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
    private Timer timer;
    private Fondo Fondo;
    private JLabel contadorLabel;

    // Constructor de la clase Main
    public Main() {
        setTitle("Sunset Skies");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        contadorLabel = new JLabel();
        contadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        contadorLabel.setForeground(Color.WHITE);
        
        Fondo = new Fondo("fondo.jpg");

        avion = new Avion(50, 300, "avion.png"); // Colocar el avion en la posision inicial
        obstaculos = new ArrayList<>(); // Inicializar la lista de obstáculos
        globos = new ArrayList<>(); 
        
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
        gamePanel.setFocusable(true); // Enfoque al panel
        gamePanel.requestFocusInWindow();
        gamePanel.add(contadorLabel);

        gamePanel.addKeyListener(avion);
        
        // Timer para contador e incrementar velocidad de obstaculos
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
        
        // Timer que actualizar la posición del avión
        timer = new Timer(10, e -> {
            avion.actualizar(); 
             for (Obstaculo obstaculo : obstaculos) {
                obstaculo.mover(); // Mover cada obstáculo
            }
            for (Globo globo : globos) {
                globo.mover(); // Mover cada obstáculo
            }
            checkCollision(); // Verificar colisiones
            gamePanel.repaint(); // Volver a dibujar el panel de juego
        });
        timer.start();
    }

    // Presenta el panel del juego
    private class GamePanel extends JPanel {
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Fondo.dibujar(g, this);
    
    // Verifica si hay explosiones para dibujarlas y eliminar las terminadas
    for (Explosion explosion : new ArrayList<>(explosiones)) {
        explosion.dibujarGif(g, this);
        if (explosion.isFinished()) {
            explosiones.remove(explosion);
        }
    }
    
    // Verifica si el avión debe estar visible o no durante la explosión
    if (!explosiones.isEmpty()) {
        avion.setVisibilidad(false); 
    } else {
        avion.setVisibilidad(true); 
    }
    
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

            reiniciarJuego();
            Explosion explosion = new Explosion(obstaculoX, obstaculoY, "explosion.gif");
            explosiones.add(explosion);

            timer.stop(); 

            int respuesta = JOptionPane.showConfirmDialog(null, "¿quieres seguir jugando?", "Has chocado", JOptionPane.YES_NO_OPTION);
            if (respuesta == JOptionPane.NO_OPTION) {
                System.exit(0); 
            }   

            TIEMPO = 0; // Reinicia el contador
            contadorLabel.setText("00:00"); 

            timer.start(); 

            return true; // Devuelve true si hay una colisión
        }
    }

    for (Globo globo : globos) {
        int globoX = globo.getX();
        int globoY = globo.getY();

        if (avionX < globoX + 50 &&
        avionX + 50 > globoX &&
        avionY < globoY + 50 &&
        avionY + 30 > globoY) {

        reiniciarJuego();
        Explosion explosion = new Explosion(globoX, globoY, "explosion.gif");
        explosiones.add(explosion); 

        timer.stop(); 

        int respuesta = JOptionPane.showConfirmDialog(null, "¿quieres seguir jugando?", "Has chocado", JOptionPane.YES_NO_OPTION);
        if (respuesta == JOptionPane.NO_OPTION) {
        System.exit(0); 
        }   
    
        TIEMPO = 0; 
        contadorLabel.setText("00:00"); 
        timer.start(); 

        return true; 
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

    globos.add(new Globo(3300, 300, 50, 50, "globo.png"));
    globos.add(new Globo(3000, 225, 50, 50, "globo2.png"));
    globos.add(new Globo(4000, 150, 50, 50, "globos.png"));

    // Reiniciar el contador
    TIEMPO = 0;
    contadorLabel.setText("00:00");

    timer.start();
}

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true); // Hacer visible la ventana del juego
    }
}