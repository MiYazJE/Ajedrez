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
    private List<List<Celda>> celdas;

    public Tablero() {
        celdas = new ArrayList<>();
        generarTablero();
        rellenarFiguras();
    }

    /**
     * Genera un tablero de size * size con los doferentes colores.
     * Almacena en un arraylist todas las celdas.
     */
    public void generarTablero() {

        int posX = 0, posY = 0;

        for (int i = 0; i < DIM; i++) {

            HBox fila = new HBox();
            if (i > 0) posY += SIZE;

            celdas.add(new ArrayList<>());
            for (int j = 0; j < DIM; j++) {
                if (j > 0) posX += SIZE;
                Celda celda = new Celda(i+j, posY, posX);
                celdas.get(i).add( celda );
                fila.getChildren().add( celda );
                System.out.println( celda );
                this.getStylesheets().add("/estilos/celdas.css");
            }

            posX = 0;
            this.getChildren().add( fila );
        }
    }

    public void rellenarFiguras() {

        int[] piezas1 = {1, 2, 3, 4, 5, 3, 2, 1};
        int[] piezas2 = {0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < piezas1.length; i++)
            celdas.get(0).get(i).establecerFigura(FIGURAS[piezas1[i]], "j1");

        for (int i = 0; i < piezas1.length; i++)
            celdas.get(1).get(i).establecerFigura(FIGURAS[piezas2[i]], "j1");

        for (int i = 0; i < piezas1.length; i++)
            celdas.get(celdas.size()-1).get(i).establecerFigura(FIGURAS[piezas1[i]], "j2");

        for (int i = 0; i < piezas1.length; i++)
            celdas.get(celdas.size()-2).get(i).establecerFigura(FIGURAS[piezas2[i]], "j2");

    }



}
