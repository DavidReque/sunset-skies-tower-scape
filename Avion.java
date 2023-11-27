import java.awt.Color;
import java.awt.Graphics;

public class Avion {
    private int x, y; // Posición del avión
    private int velocidadY; // Velocidad de movimiento vertical del avión

    // Constructor de la clase Avion que recibe las coordenadas x e y iniciales
    public Avion(int x, int y) {
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
    public void dibujar(Graphics g) {
        g.setColor(Color.RED); // Establece el color del avión a rojo
        g.fillRect(x, y, 50, 30); // Dibuja un rectángulo (avión) en la posición actual (x, y)
    }
}
