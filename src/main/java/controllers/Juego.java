/**
 * @author Rubén Saiz
 */
package controllers;

import javafx.scene.layout.BorderPane;

public class Juego extends BorderPane {

    private Tablero tablero;

    public Juego() {
        this.tablero = new Tablero();
        this.setCenter( tablero );
    }


}
