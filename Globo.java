import java.awt.Graphics;
import java.util.Random;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Globo {
    private int x, y; // Posición del obstáculo
    private int width, height; // Dimensiones del obstáculo
    private final int velocidad = 5; // Velocidad del obstáculo
    private Image imagen;

    // Constructor de la clase Globo que recibe las coordenadas x e y iniciales, el ancho y alto del globo, y la ruta de la imagen
    public Globo(int x, int y, int width, int height, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.imagen = new ImageIcon(rutaImagen).getImage();
    }

    // Método para mover el globo hacia la izquierda
    public void mover() {
        x -= velocidad; // Resta la velocidad a la coordenada x, moviendo el globo hacia la izquierda
        if (x < -width) { // Si el globo sale de la pantalla por la izquierda
            // Reposicionar el globo al azar en el lado derecho de la ventana con una posición Y aleatoria
            x = 1400;
            y = generateRandomY();
        }
    }

    // Método para generar una posición Y aleatoria dentro de la ventana
    private int generateRandomY() {
        Random random = new Random();
        return random.nextInt(600); // Cambia el valor máximo según la altura de tu ventana
    }

    // Método para dibujar el globo en la ventana
    public void dibujar(Graphics g) {
        g.drawImage(imagen, x, y, width, height, null);
    }

    // Métodos para obtener las coordenadas x e y del globo
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
