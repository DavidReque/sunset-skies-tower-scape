import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

public class Main extends JFrame {
    private final int WINDOW_WIDTH = 1200; // Ancho de la ventana
    private final int WINDOW_HEIGHT = 800; // Alto de la ventana
    private Avion avion;
    private Obstaculo obstaculo;
    private GamePanel gamePanel;
    private Timer timer; // Declarar el Timer aquí


    public Main() {
        setTitle("Sunset Skies: Tower Scape");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        avion = new Avion(50, 300);
        obstaculo = new Obstaculo(1200, 300);

        gamePanel = new GamePanel();
        add(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

        // KeyBindings para manejar las teclas de flecha
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released UP"), "Released Up");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "Down");
        gamePanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released DOWN"), "Released Down");

        gamePanel.getActionMap().put("Up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.moverArriba();
            }
        });

        gamePanel.getActionMap().put("Released Up", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.detener();
            }
        });

        gamePanel.getActionMap().put("Down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.moverAbajo();
            }
        });

        gamePanel.getActionMap().put("Released Down", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                avion.detener();
            }
        });

        timer = new Timer(10, e -> {
            avion.actualizar();
            obstaculo.mover();
            checkCollision();
            gamePanel.repaint();
        });
        timer.start();
    }

    private class GamePanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            avion.dibujar(g);
            obstaculo.dibujar(g);
        }
    }

    private void checkCollision() {
        int avionX = avion.getX();
        int avionY = avion.getY();
        int obstaculoX = obstaculo.getX();
        int obstaculoY = obstaculo.getY();

        if (avionX < obstaculoX + 50 &&
            avionX + 50 > obstaculoX &&
            avionY < obstaculoY + 50 &&
            avionY + 30 > obstaculoY) {
            // Colisión detectada, realiza la lógica correspondiente (por ejemplo, reiniciar el juego)
            reiniciarJuego();
        }
    }
    
    private void reiniciarJuego() {
        // Detener el temporizador
        timer.stop();

        // Restablecer las posiciones iniciales
        avion = new Avion(50, 300);
        obstaculo = new Obstaculo(1200, 300);

        // Volver a iniciar el temporizador
        timer.start();
    }

    public static void main(String[] args) {
        Main game = new Main();
        game.setVisible(true);
    }
}
