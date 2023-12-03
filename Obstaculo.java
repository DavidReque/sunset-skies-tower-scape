import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstaculo {
    private int x, y; // Posición del obstáculo
    private int width, height; // Dimensiones del obstáculo
    private final int velocidad = 5; // Velocidad del obstáculo
    private Color color; // Color del obstáculo

    // Constructor de la clase Obstaculo que recibe las coordenadas x e y iniciales, el color, ancho y alto del obstáculo
    public Obstaculo(int x, int y, Color color, int width, int height) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.width = width;
        this.height = height;
    }

    // Método para mover el obstáculo hacia la izquierda
    public void mover() {
        x -= velocidad; // Resta la velocidad a la coordenada x, moviendo el obstáculo hacia la izquierda
        if (x < -width) { // Si el obstáculo sale de la pantalla por la izquierda
            x = 1200; // Reposiciona el obstáculo al final de la ventana en el lado derecho
            Random rand = new Random();
            y = 513 + rand.nextInt(100); // Asigna una posición vertical aleatoria para los edificios en la parte inferior de la ventana
        }
    }

    // Método para dibujar el obstáculo en la ventana
    public void dibujar(Graphics g) {
        g.setColor(color); // Establece el color del obstáculo
        g.fillRect(x, y, width, height); // Dibuja el obstáculo con las dimensiones y color especificados
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
