import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstaculo {
    private int x, y; // Posición del obstáculo
    private final int velocidad = 5; // Velocidad del obstáculo

    // Constructor de la clase Obstaculo que recibe las coordenadas x e y iniciales
    public Obstaculo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    // Método para mover el obstáculo hacia la izquierda
    public void mover() {
        x -= velocidad; // Resta la velocidad a la coordenada x, moviendo el obstáculo hacia la izquierda
        if (x < -50) { // Si el obstáculo sale de la pantalla por la izquierda
            x = 1200; // Reposiciona el obstáculo al final de la ventana en el lado derecho
            Random rand = new Random();
            y = rand.nextInt(600); // Asigna una posición vertical aleatoria dentro del rango de la ventana
        }
    }

    // Método para dibujar el obstáculo en la ventana
    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE); // Establece el color del obstáculo a azul
        g.fillRect(x, y, 50, 50); // Dibuja un rectángulo (obstáculo) en la posición actual (x, y)
    }

    // Método para obtener la coordenada x del obstáculo
    public int getX() {
        return x;
    }

    // Método para obtener la coordenada y del obstáculo
    public int getY() {
        return y;
    }
}
