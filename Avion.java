import java.awt.Color;
import java.awt.Graphics;

public class Avion {
    private int x, y; // Posición del avión
    private int velocidadY; // Velocidad de movimiento vertical del avión

    public Avion(int x, int y) {
        this.x = x;
        this.y = y;
        this.velocidadY = 0; // Inicialmente sin movimiento
    }

    public void actualizar() {
        y += velocidadY; // Actualiza la posición vertical del avión
    }

    public void moverArriba() {
        velocidadY = -3; // Mueve hacia arriba
    }

    public void moverAbajo() {
        velocidadY = 3; // Mueve hacia abajo
    }

    public void detener() {
        velocidadY = 0; // Detiene el movimiento vertical
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void dibujar(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x, y, 50, 30); // Dibuja el avión en la posición actual
    }
}
