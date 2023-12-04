import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Obstaculo {
    private int x, y; // Posición del obstáculo
    private int width, height; // Dimensiones del obstáculo
    private int velocidad = 5; // Velocidad del obstáculo
    private Image imagen;

    // Constructor de la clase Obstaculo que recibe las coordenadas x e y iniciales, el color, ancho y alto del obstáculo
    public Obstaculo(int x, int y,int width, int height, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagen = new ImageIcon(rutaImagen).getImage();
    }

    // Método para mover el obstáculo hacia la izquierda
    public void mover() {
    x -= velocidad; // Resta la velocidad a la coordenada x, moviendo el obstáculo hacia la izquierda
    if (x < -width) { // Si el obstáculo sale de la pantalla por la izquierda
        x = 1200; // Reposiciona el obstáculo al final de la ventana en el lado derecho
    }
}

    // Método para dibujar el obstáculo en la ventana
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }

    // Método para obtener la coordenada x del obstáculo
    public int getX() {
        return x;
    }

    // Método para obtener la coordenada y del obstáculo
    public int getY() {
        return y;
    }
    
    public void incrementarVelocidad() {
        velocidad += 5; // Incrementa la velocidad en 1
    }
}
