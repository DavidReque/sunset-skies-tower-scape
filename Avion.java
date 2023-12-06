import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Avion implements KeyListener {
    private int x, y; // Posición del avión
    private int velocidadY; // Velocidad de movimiento vertical del avión
    private Image imagen;
    private boolean visible; // Indica si el avión es visible o no
    
    @Override
    public void keyTyped(KeyEvent e) {
        // Código relacionado a la tecla presionada pero sin implementación específica en este caso
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            moverArriba();
        } else if (key == KeyEvent.VK_DOWN) {
            moverAbajo();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            detener();
        }
    }

    // Constructor de la clase Avion que recibe las coordenadas x e y iniciales
    public Avion(int x, int y, String rutaImagen) {
        this.imagen = new ImageIcon(rutaImagen).getImage();
        this.x = x;
        this.y = y;
        this.velocidadY = 0; // Inicialmente sin movimiento
        this.visible = true; // Por defecto, el avión es visible
    }

    // Métodos para actualizar la posición vertical del avión
    public void actualizar() {
        y += velocidadY; // Actualiza la posición vertical del avión basándose en la velocidad vertical
    }

    // Métodos para mover el avión hacia arriba y hacia abajo
    public void moverArriba() {
        velocidadY = -3; // Establece una velocidad negativa para mover el avión hacia arriba
    }

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

    // Método para establecer la visibilidad del avión
    public void setVisibilidad(boolean visible) {
        this.visible = visible;
    }

    // Método para obtener la visibilidad actual del avión
    public boolean isVisibilidad() {
        return visible;
    }

    // Método para dibujar el avión en la ventana
    public void dibujar(Graphics g, JPanel panel) {
        if (visible) {
            g.drawImage(imagen, x, y, 80, 80, panel);
        }
    }
}
