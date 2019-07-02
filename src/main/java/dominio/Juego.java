/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.scene.layout.BorderPane;

public class Juego extends BorderPane {

    private Tablero tablero;
    private int turno;
    private Jugador j1;
    private Jugador j2;

    public Juego() {
        this.tablero = new Tablero();
        this.setCenter( tablero );
        j1 = new Jugador("j1");
        j2 = new Jugador("j2");
    }


}
