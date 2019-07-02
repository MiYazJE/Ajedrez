/**
 * @author Rubén Saiz
 */
package controllers;

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
    }

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
            }

            posX = 0;
            this.getChildren().add( fila );
        }

    }

}
