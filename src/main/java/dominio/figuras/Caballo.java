/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;
import dominio.Tablero;

import java.util.ArrayList;

public class Caballo extends Figura {

    @Override
    public void crearPredicciones(Celda[][] celdas, Celda celdaActual, ArrayList<Celda> predicciones) {

        int[] rowDirections = {-2, -1, -2, -1,  1,  2, 2, 1};
        int[] colDirections = {-1, -2,  1,  2, -2, -1, 1, 2};
        int posF, posC;
        predicciones = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            posF = celdaActual.posY + rowDirections[i];
            posC = celdaActual.posX + colDirections[i];
            if (posF < 0 || posF >= 8 || posC < 0 || posC >= 8)
                continue;
            Celda comprobar = Tablero.celdas[posF][posC];
            if (Celda.esValida( comprobar )) {
                comprobar.pintarPosibleMovimiento();
                predicciones.add( celdaActual );
            }
        }

    }

    @Override
    public void mover() {

    }
}
