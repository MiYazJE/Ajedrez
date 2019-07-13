/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public class Rey extends Figura {

    @Override
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {

        ArrayList<Celda> predicciones = new ArrayList<>();

        int[] rowDirections = {-1, -1, -1, 0,  0,  1, 1, 1};
        int[] colDirections = {0,  -1, 1,  1, -1, -1, 1, 0};
        int f, c;

        for (int i = 0; i < 8; i++) {
            f = celdaActual.posY + rowDirections[i];
            c = celdaActual.posX + colDirections[i];
            if (esValido(f, c)) {
                Celda comprobar = tablero[f][c];
                if (Celda.esValida(comprobar)) {
                    comprobar.pintarPosibleMovimiento();
                    predicciones.add(comprobar);
                }
            }
        }

        return predicciones;
    }

    private boolean esValido(int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }

}
