import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

public class Main extends JFrame {
    private final int WINDOW_WIDTH = 1200; // Ancho de la ventana
    private final int WINDOW_HEIGHT = 800; // Alto de la ventana
    private int TIEMPO = 0;
    private Avion avion;
    private List<Obstaculo> obstaculos; // Lista de obstáculos
    private List<Globo> globos; 
    private List<Explosion> explosiones;
    private GamePanel gamePanel;
    private Timer timer; // Declarar el Timer aquí
    private Fondo Fondo;
    private JLabel contadorLabel;

    // Constructor de la clase Main
    public Main() {
        setTitle("Sunset Skies: Tower Scape");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        contadorLabel = new JLabel();
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setForeground(Color.WHITE);
        
        Fondo = new Fondo("fondo.jpg");

        avion = new Avion(50, 300, "avion.png"); // Crear una instancia del avión en la posición inicial
        obstaculos = new ArrayList<>(); // Inicializar la lista de obstáculos
        globos = new ArrayList<>(); // Inicializar la lista de obstáculos
        
        explosiones = new ArrayList<>();
        
        globos.add(new Globo(3300, 300, 50, 50, "globo.png"));
        globos.add(new Globo(3000, 225, 50, 50, "globo2.png"));
        globos.add(new Globo(4000, 150, 50, 50, "globos.png"));
        
        obstaculos.add(new Obstaculo(1200, 500, 275, 275, "edificio.png"));
        obstaculos.add(new Obstaculo(1500, 450, 320, 330, "edificio2.png"));
        obstaculos.add(new Obstaculo(1800, 425, 350, 340, "edificio3.png"));
        obstaculos.add(new Obstaculo(2200, 563, 200, 200, "casa.png"));
        obstaculos.add(new Obstaculo(2800, 500, 280, 298, "edificio4.png"));
        
        gamePanel = new GamePanel(); // Crear un panel para el juego
        add(gamePanel); // Agregar el panel al JFrame
        gamePanel.setFocusable(true); // Permitir que el panel obtenga el enfoque
        gamePanel.requestFocusInWindow(); // Solicitar el enfoque al panel
        gamePanel.add(contadorLabel);
        
        // Configurar los KeyBindings para manejar las teclas de flecha
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "Released Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "Released Down");

        gamePanel.addKeyListener(avion);
        
        timer = new Timer(1000, e -> {
        TIEMPO++;
        int minutos = TIEMPO / 60;
        int segundos = TIEMPO % 60;
        contadorLabel.setText(String.format("%02d:%02d", minutos, segundos));
    
        // Incrementar la velocidad de los obstáculos y globos cada 30 segundos
        if (TIEMPO % 30 == 0) {
        for (Obstaculo obstaculo : obstaculos) {
            obstaculo.incrementarVelocidad();
        }
        for (Globo globo : globos) {
            globo.incrementarVelocidad();
        }
        }

        // Verificar las colisiones y reiniciar el tiempo transcurrido si hay una colisión
        if (checkCollision()) {
        TIEMPO = 0;
        contadorLabel.setText("00:00");
        }
        });
        timer.start();
        
        timer = new Timer(10, e -> {
            avion.actualizar(); // Actualizar la posición del avión
             for (Obstaculo obstaculo : obstaculos) {
                obstaculo.mover(); // Mover cada obstáculo
            }
            for (Globo globo : globos) {
                globo.mover(); // Mover cada obstáculo
            }
            checkCollision(); // Verificar colisiones
            gamePanel.repaint(); // Volver a dibujar el panel de juego
        });
        timer.start(); // Iniciar el temporizador
    }

    // Clase interna GamePanel que extiende JPanel para representar el panel de juego
    private class GamePanel extends JPanel {
        // Dentro de la clase GamePanel
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Fondo.dibujar(g, this);
    
    // Verifica si hay explosiones para dibujarlas y eliminar las terminadas
    for (Explosion explosion : new ArrayList<>(explosiones)) {
        explosion.draw(g, this);
        if (explosion.isFinished()) {
            explosiones.remove(explosion);
        }
    }
    
    // Verifica si el avión debe estar visible o no (durante la explosión)
    if (!explosiones.isEmpty()) {
        avion.setVisibilidad(false); // Si hay explosiones, el avión desaparece
    } else {
        avion.setVisibilidad(true); // Si no hay explosiones, el avión es visible
    }
    
    // Dibuja el avión si está visible
    if (avion.isVisibilidad()) {
        avion.dibujar(g, this);
    }
    
    // Dibujar obstáculos y globos
    for (Obstaculo obstaculo : obstaculos) {
        obstaculo.dibujar(g);
    }       
    for (Globo globo : globos) {
        globo.dibujar(g);
    }
}
}

    // Método para verificar colisiones entre el avión y el obstáculo
    private boolean checkCollision() {
    int avionX = avion.getX();
    int avionY = avion.getY();

    for (Obstaculo obstaculo : obstaculos) {
        int obstaculoX = obstaculo.getX();
        int obstaculoY = obstaculo.getY();

        if (avionX < obstaculoX + 50 &&
            avionX + 50 > obstaculoX &&
            avionY < obstaculoY + 50 &&
            avionY + 30 > obstaculoY) {
            // Si hay colisión con un obstáculo, se ejecuta la lógica para reiniciar el juego
            reiniciarJuego();
            Explosion explosion = new Explosion(obstaculoX, obstaculoY, "explosion.gif");
            explosiones.add(explosion); // Agrega la nueva explosión a la lista de explosiones

            timer.stop(); // Detiene el Timer
            // Muestra un cuadro de diálogo preguntando si el jugador quiere jugar de nuevo
            int respuesta = JOptionPane.showConfirmDialog(null, "¿quieres seguir jugando?", "Has chocado", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                System.exit(0); // Cierra el juego si el jugador selecciona "No"
            }   

            TIEMPO = 0; // Reinicia el contador
            contadorLabel.setText("00:00"); // Actualiza el texto del contador a cero

            timer.start(); // Reinicia el Timer

            return true; // Devuelve true si hay una colisión
             // Devuelve true si hay una colisión
        }
    }

    for (Globo globo : globos) {
        int globoX = globo.getX();
        int globoY = globo.getY();

        if (avionX < globoX + 50 &&
        avionX + 50 > globoX &&
        avionY < globoY + 50 &&
        avionY + 30 > globoY) {
        // Si hay colisión con un obstáculo, se ejecuta la lógica para reiniciar el juego
        reiniciarJuego();
        Explosion explosion = new Explosion(globoX, globoY, "explosion.gif");
        explosiones.add(explosion); // Agrega la nueva explosión a la lista de explosiones

        timer.stop(); // Detiene el Timer
        // Muestra un cuadro de diálogo preguntando si el jugador quiere jugar de nuevo
        int respuesta = JOptionPane.showConfirmDialog(null, "¿quieres seguir jugando?", "Has chocado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.NO_OPTION) {
        System.exit(0); // Cierra el juego si el jugador selecciona "No"
        }   
    
        TIEMPO = 0; // Reinicia el contador
        contadorLabel.setText("00:00"); // Actualiza el texto del contador a cero
        timer.start(); // Reinicia el Timer

        return true; // Devuelve true si hay una colisión
        }   
    }
    
    return false; // Devuelve false si no hay ninguna colisión
}
    
    // Método para reiniciar el juego
    private void reiniciarJuego() {
    // Detener el temporizador
    timer.stop();

    // Restablecer las listas a su estado inicial
    obstaculos.clear();
    globos.clear();

    // Volver a agregar los obstáculos iniciales
    obstaculos.add(new Obstaculo(1200, 500, 275, 275, "edificio.png"));
    obstaculos.add(new Obstaculo(1500, 450, 320, 330, "edificio2.png"));
    obstaculos.add(new Obstaculo(1800, 425, 350, 340, "edificio3.png"));
    obstaculos.add(new Obstaculo(2200, 563, 200, 200, "casa.png"));
    obstaculos.add(new Obstaculo(2800, 500, 280, 298, "edificio4.png"));

    // Volver a agregar los globos iniciales
    globos.add(new Globo(3300, 300, 50, 50, "globo.png"));
    globos.add(new Globo(3000, 225, 50, 50, "globo2.png"));
    globos.add(new Globo(4000, 150, 50, 50, "globos.png"));

    // Reiniciar el contador
    TIEMPO = 0;
    contadorLabel.setText("00:00");

    // Volver a iniciar el temporizador
    timer.start();
}

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true); // Hacer visible la ventana del juego
    }
}