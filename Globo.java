import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Globo {
    private int x, y;
    private int width, height; 
    private int velocidad = 5; 
    private Image imagen;

    public Globo(int x, int y, int width, int height, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagen = new ImageIcon(rutaImagen).getImage();
    }

    public void mover() {
        x -= velocidad; 
        if (x < -width) { 
            x = 1400;
            y = globoRandom();
        }
    }

    // Método para generar una posición Y aleatoria dentro de la ventana
    private int globoRandom() {
        Random random = new Random();
        return random.nextInt(600); // Cambia el valor de la altura
    }

    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public void incrementarVelocidad() {
        velocidad += 4;
    }
}
