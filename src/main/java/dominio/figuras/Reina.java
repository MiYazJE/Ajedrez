/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public class Reina extends Figura {

    public Reina() {
        super();
    }

    @Override
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {
        return new ArrayList<Celda>();
    }

    @Override
    public void mover() {

    }
}
