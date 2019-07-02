/**
 * @author Rubén Saiz
 */
package controllers;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Celda extends Rectangle implements PropiedadesCelda {

    private String figura;
    private boolean blancas;
    private int posY;
    private int posX;

    public Celda(int blanca, int posY, int posX) {
        this.blancas = (blanca % 2 == 0);
        crearCelda();
        this.posY = posY;
        this.posX = posX;
    }

    /**
     * Inicializa la celda.
     */
    private void crearCelda() {
        this.setWidth( SIZE );
        this.setHeight( SIZE );
        this.setFill(getColor());
    }

    /**
     * Obtiene el color de la celda en base a la variable boleana "blancas".
     */
    private Color getColor() {
        return (blancas) ? Color.LEMONCHIFFON : Color.GOLDENROD;
    }

    /**
     *  Cambia el estado actual de la celda, es decir, la figura que contiene.
     */
    public void cambiarEstado() {

    }

    public void setFigura(String tipo) {
        this.figura = tipo;
        if (!this.figura.equals("VACIO"))
            pintarFigura();
    }

    private void pintarFigura() {

    }

    @Override
    public String toString() {
        return String.format("%d - %d \n", posY, posX);
    }
}
