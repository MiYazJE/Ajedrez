/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;
import dominio.Tablero;

import java.util.ArrayList;

public class Caballo extends Figura {

    @Override
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {

        ArrayList<Celda> predicciones = new ArrayList<>();
        int[] rowDirections = {-2, -1, -2, -1,  1,  2, 2, 1};
        int[] colDirections = {-1, -2,  1,  2, -2, -1, 1, 2};
        int posF, posC;

        for (int i = 0; i < 8; i++) {
            posF = celdaActual.posY + rowDirections[i];
            posC = celdaActual.posX + colDirections[i];
            if (posF < 0 || posF >= 8 || posC < 0 || posC >= 8)
                continue;
            Celda comprobar = tablero[posF][posC];
            if (Celda.esValida( comprobar )) {
                comprobar.pintarPosibleMovimiento();
                predicciones.add( comprobar );
            }
        }
        return predicciones;
    }

}
