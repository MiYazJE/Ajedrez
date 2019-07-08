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

    public abstract void crearPredicciones(Celda[][] celdas, Celda celdaActual, ArrayList<Celda> predicciones);

    public abstract void mover();


}
