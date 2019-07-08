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

        ArrayList<Celda> predicciones = new ArrayList<>();

        /*

        * 0 0 * 0 0 * 0
        0 * 0 * 0 * 0 0
        0 0 * * * 0 0 0
        * * * X * * * *
        0 0 * * * 0 0 0
        0 * 0 * 0 * 0 0
        * 0 0 * 0 0 * 0
        0 0 0 * 0 0 0 *

         */

        for (int i = celdaActual.posY+1; i < 8; i++) {
            Celda comprobar = tablero[i][celdaActual.posX];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        for (int i = celdaActual.posY-1; i >= 0; i--) {
            Celda comprobar = tablero[i][celdaActual.posX];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        for (int i = celdaActual.posX+1; i < 8; i++) {
            Celda comprobar = tablero[celdaActual.posY][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        for (int i = celdaActual.posX-1; i >= 0; i--) {
            Celda comprobar = tablero[celdaActual.posY][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        int aux = celdaActual.posX;
        for (int i = celdaActual.posY+1; i < 8; i++) {
            ++aux;
            if (!valid(aux, i)) break;
            Celda comprobar = tablero[i][aux];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        aux = celdaActual.posX;
        for (int i = celdaActual.posY-1; i >= 0; i--) {
            --aux;
            if (!valid(aux, i)) break;
            Celda comprobar = tablero[i][aux];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        // arriba derecha
        aux = celdaActual.posY;
        for (int i = celdaActual.posX+1; i < 8; i++) {
            --aux;
            if (!valid(aux, i)) break;
            Celda comprobar = tablero[aux][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        aux = celdaActual.posY;
        for (int i = celdaActual.posX-1; i >= 0; i--) {
            ++aux;
            if (!valid(aux, i)) break;
            Celda comprobar = tablero[aux][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        return predicciones;
    }

    private boolean valid(int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }

    @Override
    public void mover() {

    }
}
