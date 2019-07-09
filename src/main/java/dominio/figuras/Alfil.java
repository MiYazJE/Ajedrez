/**
 * @author Rubén Saiz
 */
package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public class Alfil extends Figura {
    @Override
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {

        ArrayList<Celda> predicciones = new ArrayList<>();

        int aux = celdaActual.posX;
        for (int i = celdaActual.posY+1; i < 8; i++) {
            ++aux;
            if (!esValido(aux, i)) break;
            Celda comprobar = tablero[i][aux];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        aux = celdaActual.posX;
        for (int i = celdaActual.posY-1; i >= 0; i--) {
            --aux;
            if (!esValido(aux, i)) break;
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
            if (!esValido(aux, i)) break;
            Celda comprobar = tablero[aux][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        aux = celdaActual.posY;
        for (int i = celdaActual.posX-1; i >= 0; i--) {
            ++aux;
            if (!esValido(aux, i)) break;
            Celda comprobar = tablero[aux][i];
            if (!Celda.esValida(comprobar))
                break;
            comprobar.pintarPosibleMovimiento();
            predicciones.add(comprobar);
        }

        return predicciones;
    }

    private boolean esValido(int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }

    @Override
    public void mover() {

    }
}
