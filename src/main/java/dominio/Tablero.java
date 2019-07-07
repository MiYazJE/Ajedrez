/**
 * @author Rub�n Saiz
 */
package dominio;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class Tablero extends VBox implements PropiedadesCelda {

    private static final int DIM = 8;
    public static List<List<Celda>> celdas;
    public static Jugador j1;
    public static Jugador j2;

    public Tablero(String nameJ1, String nameJ2) {
        celdas = new ArrayList<>();
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
            celdas.add(new ArrayList<>());

            for (int j = 0; j < DIM; j++) {
                Celda celda = new Celda(i+j, i, j);
                celdas.get(i).add( celda );
                fila.getChildren().add( celda );
                this.getStylesheets().add("/estilos/celdas.css");
            }

            this.getChildren().add( fila );
        }

    }

    private void rellenarFiguras() {

        /*int[] piezas1 = {2, 3, 4, 5, 6, 4, 3, 2};

        for (int i = 0; i < 8; i++){
            celdas.get(0).get(i).establecerFigura(piezas1[i], j2.getNombre());
            j2.getFichas().add(celdas.get(0).get(i));

            celdas.get(celdas.size()-1).get(i).establecerFigura(piezas1[i], j1.getNombre());
            j1.getFichas().add(celdas.get(celdas.size()-1).get(i));

            celdas.get(1).get(i).establecerFigura(PEON, j2.getNombre());
            j2.getFichas().add(celdas.get(1).get(i));

            celdas.get(celdas.size()-2).get(i).establecerFigura(PEON, j1.getNombre());
            j1.getFichas().add(celdas.get(celdas.size()-2).get(i));
        }*/

        celdas.get(3).get(3).establecerFigura(CABALLO, j2.getNombre());



    }

}
