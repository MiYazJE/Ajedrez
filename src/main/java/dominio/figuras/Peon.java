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
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {
        ArrayList<Celda> predicciones = new ArrayList<>();
        if (celdaActual.getJugador().equals("j1")) {
            if (celdaActual.posY == 0) return predicciones;
            tablero[celdaActual.posY-1][celdaActual.posX].pintarPosibleMovimiento();
            predicciones.add(tablero[celdaActual.posY-1][celdaActual.posX]);
        }
        else {
            if (celdaActual.posY == 0) return predicciones;
            tablero[celdaActual.posY+1][celdaActual.posX].pintarPosibleMovimiento();
            predicciones.add(tablero[celdaActual.posY+1][celdaActual.posX]);
        }
        return predicciones;
    }

    @Override
    public void mover() {

    }
}
