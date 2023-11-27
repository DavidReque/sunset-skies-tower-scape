import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Obstaculo {
    private int x, y; // Posición del obstáculo
    private final int velocidad = 5; // Velocidad del obstáculo

    public Obstaculo(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void mover() {
        x -= velocidad; // Mueve el obstáculo hacia la izquierda
        if (x < -50) { // Si el obstáculo sale de la pantalla
            x = 1200; // Lo reposiciona al final de la ventana
            Random rand = new Random();
            y = rand.nextInt(600); // Asigna una posición vertical aleatoria
        }
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 50); // Dibuja el obstáculo en la posición actual
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
