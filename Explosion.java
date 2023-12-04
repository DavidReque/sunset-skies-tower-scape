import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

public class Explosion {
    private Image imagen;
    private int x, y;
    private int contador = 0; // Contador para controlar cuánto tiempo se muestra la explosión
    private static final int DURACION_EXPLOSION = 50; // Duración de la explosión en milisegundos

    public Explosion(int x, int y, String rutaImagen) {
        this.x = x;
        this.y = y;
        this.imagen = new ImageIcon(rutaImagen).getImage();
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(imagen, x, y, observer);
        contador++;
    }

    public boolean isFinished() {
        return contador > DURACION_EXPLOSION;
    }
}
