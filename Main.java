import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private final int WINDOW_WIDTH = 1200; // Ancho de la ventana
    private final int WINDOW_HEIGHT = 800; // Alto de la ventana
    private Avion avion;
    private List<Obstaculo> obstaculos; // Lista de obstáculos
    private GamePanel gamePanel;
    private Timer timer; // Declarar el Timer aquí
    private Fondo Fondo;

    // Constructor de la clase Main
    public Main() {
        setTitle("Sunset Skies: Tower Scape");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        Fondo = new Fondo("fondo.jpg");

        avion = new Avion(50, 300, "avion.png"); // Crear una instancia del avión en la posición inicial
        obstaculos = new ArrayList<>(); // Inicializar la lista de obstáculos

        obstaculos.add(new Obstaculo(1200, 570, Color.BLUE, 50, 200));
        obstaculos.add(new Obstaculo(1500, 600, Color.RED, 70, 150));
        obstaculos.add(new Obstaculo(1800, 530, Color.GREEN, 60, 180));
        
        gamePanel = new GamePanel(); // Crear un panel para el juego
        add(gamePanel); // Agregar el panel al JFrame
        gamePanel.setFocusable(true); // Permitir que el panel obtenga el enfoque
        gamePanel.requestFocusInWindow(); // Solicitar el enfoque al panel

        // Configurar los KeyBindings para manejar las teclas de flecha
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "Released Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "Released Down");

        // Configurar las acciones para las teclas de flecha
        gamePanel.getActionMap().put("Up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.moverArriba(); // Mover el avión hacia arriba cuando se presiona la tecla hacia arriba
            }
        });

        gamePanel.getActionMap().put("Released Up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.detener(); // Detener el movimiento del avión cuando se suelta la tecla hacia arriba
            }
        });

        gamePanel.getActionMap().put("Down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.moverAbajo(); // Mover el avión hacia abajo cuando se presiona la tecla hacia abajo
            }
        });

        gamePanel.getActionMap().put("Released Down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.detener(); // Detener el movimiento del avión cuando se suelta la tecla hacia abajo
            }
        });

        timer = new Timer(10, e -> {
            avion.actualizar(); // Actualizar la posición del avión
             for (Obstaculo obstaculo : obstaculos) {
                obstaculo.mover(); // Mover cada obstáculo
            }
            checkCollision(); // Verificar colisiones
            gamePanel.repaint(); // Volver a dibujar el panel de juego
        });
        timer.start(); // Iniciar el temporizador
    }

    // Clase interna GamePanel que extiende JPanel para representar el panel de juego
    private class GamePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Fondo.dibujar(g, this);
            avion.dibujar(g, this); // Dibujar el avión en el panel de juego
            for (Obstaculo obstaculo : obstaculos) {
                obstaculo.dibujar(g);
            }        
        }
    }

    // Método para verificar colisiones entre el avión y el obstáculo
    private void checkCollision() {
        int avionX = avion.getX();
        int avionY = avion.getY();
        
        for (Obstaculo obstaculo : obstaculos) {
        int obstaculoX = obstaculo.getX();
        int obstaculoY = obstaculo.getY();

        if (avionX < obstaculoX + 50 &&
            avionX + 50 > obstaculoX &&
            avionY < obstaculoY + 50 &&
            avionY + 30 > obstaculoY) {
            // Si hay colisión, se ejecuta la lógica para reiniciar el juego
            reiniciarJuego();
        }
    }
}
    
    // Método para reiniciar el juego
    private void reiniciarJuego() {
        // Detener el temporizador
        timer.stop();

        // Restablecer las posiciones iniciales del avión y el obstáculo
        avion = new Avion(50, 300, "avion.png");
        obstaculos = new ArrayList<>();
        obstaculos.add(new Obstaculo(1200, 570, Color.BLUE, 50, 200));
        obstaculos.add(new Obstaculo(1500, 600, Color.RED, 70, 150));
        obstaculos.add(new Obstaculo(1800, 530, Color.GREEN, 60, 180));
        
        // Volver a iniciar el temporizador
        timer.start();
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true); // Hacer visible la ventana del juego
    }
}