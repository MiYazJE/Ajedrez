/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Tablero extends VBox implements PropiedadesCelda {

    private static final int DIM = 8;
    public static Celda[][] celdas;
    public static Jugador j1;
    public static Jugador j2;

    public Tablero(String nameJ1, String nameJ2) {
        celdas = new Celda[DIM][DIM];
        j1 = new Jugador(nameJ1);
        j2 = new Jugador(nameJ2);
        generarTablero();
        rellenarFiguras();
    }

    /**
     * Genera un tablero de size * size con los doferentes colores.
     * Almacena en un arraylist todas las celdas.
     */
    private void generarTablero() {

        for (int i = 0; i < DIM; i++) {

            HBox fila = new HBox();

            for (int j = 0; j < DIM; j++) {
                Celda celda = new Celda(i+j, i, j);
                celdas[i][j] = celda ;
                fila.getChildren().add( celda );
                this.getStylesheets().add("/estilos/celdas.css");
            }

            this.getChildren().add( fila );
        }

    }

    private void rellenarFiguras() {

        int[] fichas = {TORRE, CABALLO, ALFIL, REINA, REY, ALFIL, CABALLO, TORRE};

        for (int i = 0; i < 8; i++){
            celdas[0][i].establecerFigura(fichas[i], j2.getNombre());
            j2.getFichas().add(celdas[0][i]);

            celdas[celdas.length-1][i].establecerFigura(fichas[i], j1.getNombre());
            j1.getFichas().add(celdas[celdas.length-1][i]);

            celdas[1][i].establecerFigura(PEON, j2.getNombre());
            j2.getFichas().add(celdas[1][i]);

            celdas[celdas.length-2][i].establecerFigura(PEON, j1.getNombre());
            j1.getFichas().add(celdas[celdas.length-2][i]);
        }

        /*celdas[0][0].establecerFigura(REINA, j1.getNombre());
        celdas[4][4].establecerFigura(ALFIL, j1.getNombre());
        celdas[7][3].establecerFigura(REY, j1.getNombre()); */


    }

}
