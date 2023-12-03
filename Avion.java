import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Avion {
    private int x, y; // Posición del avión
    private int velocidadY; // Velocidad de movimiento vertical del avión
    private Image imagen;

    // Constructor de la clase Avion que recibe las coordenadas x e y iniciales
    public Avion(int x, int y, String rutaImagen) {
        this.imagen = new ImageIcon(rutaImagen).getImage();
        this.x = x;
        this.y = y;
        this.velocidadY = 0; // Inicialmente sin movimiento
    }

    // Método para actualizar la posición vertical del avión
    public void actualizar() {
        y += velocidadY; // Actualiza la posición vertical del avión basándose en la velocidad vertical
    }

    // Método para mover el avión hacia arriba
    public void moverArriba() {
        velocidadY = -3; // Establece una velocidad negativa para mover el avión hacia arriba
    }

    // Método para mover el avión hacia abajo
    public void moverAbajo() {
        velocidadY = 3; // Establece una velocidad positiva para mover el avión hacia abajo
    }

    // Método para detener el movimiento vertical del avión
    public void detener() {
        velocidadY = 0; // Establece la velocidad vertical a cero para detener el movimiento
    }

    // Método para obtener la coordenada x del avión
    public int getX() {
        return x;
    }

    // Método para obtener la coordenada y del avión
    public int getY() {
        return y;
    }

    // Método para dibujar el avión en la ventana
    public void dibujar(Graphics g, JPanel panel) {
        g.drawImage(imagen, x, y, 80, 80, panel);
    }
}
