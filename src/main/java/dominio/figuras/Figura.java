/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public abstract class Figura {

    // Si el rey se encuentra atascado por esta
    private boolean atacada;

    public Figura() {
        this.atacada = false;
    }

    public abstract ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual);

    public abstract void mover();


}
