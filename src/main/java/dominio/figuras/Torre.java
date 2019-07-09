package dominio.figuras;

import dominio.Celda;

import java.util.ArrayList;

public class Torre extends Figura {

    @Override
    public ArrayList<Celda> crearPredicciones(Celda[][] tablero, Celda celdaActual) {

        ArrayList<Celda> predicciones = new ArrayList<>();

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

        return predicciones;
    }

    @Override
    public void mover() {

    }
}
