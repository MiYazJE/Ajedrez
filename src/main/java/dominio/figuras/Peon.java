/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public class Peon extends Figura {

    public Peon() {
        super();
    }

    @Override
    public void crearPredicciones(Celda[][] celdas, Celda celdaActual, ArrayList<Celda> predicciones) {
        predicciones = new ArrayList<>();
        if (celdaActual.getJugador().equals("j1")) {
            if (celdaActual.posY == 0) return;
            celdas[celdaActual.posY-1][celdaActual.posX].pintarPosibleMovimiento();
            predicciones.add(celdas[celdaActual.posY-1][celdaActual.posX]);
        }
        else {
            if (celdaActual.posY == 0) return;
            celdas[celdaActual.posY+1][celdaActual.posX].pintarPosibleMovimiento();
            predicciones.add(celdas[celdaActual.posY+1][celdaActual.posX]);
        }
    }

    @Override
    public void mover() {

    }
}
