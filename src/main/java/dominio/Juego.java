/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.scene.layout.BorderPane;

public class Juego extends BorderPane {

    private Tablero tablero;
    private int turno;

    public Juego() {
        this.tablero = new Tablero("j1", "j2");
        this.setCenter( tablero );
    }





}
