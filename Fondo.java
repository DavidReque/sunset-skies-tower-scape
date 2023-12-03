import javax.swing.*;
import java.awt.*;

public class Fondo {
    private Image imagen;

    public Fondo(String rutaImagen) {
        imagen = new ImageIcon(rutaImagen).getImage();
    }

    public void dibujar(Graphics g, JPanel panel) {
        g.drawImage(imagen, 0, 0, panel.getWidth(), panel.getHeight(), panel);
    }
}
