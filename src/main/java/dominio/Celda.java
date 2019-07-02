/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Celda extends StackPane implements PropiedadesCelda {

    private StringProperty nombreFigura;
    private boolean blancas;
    private int posY;
    private int posX;
    private String jugador;
    private Image figura;

    public Celda(int blanca, int posY, int posX) {
        nombreFigura = new SimpleStringProperty();
        this.blancas = (blanca % 2 == 0);
        crearCelda();
        crearEventos();
        this.posY = posY;
        this.posX = posX;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getJugador() {
        return jugador;
    }

    public void setNombreFigura(String nombreFigura) {
        this.nombreFigura.set(nombreFigura);
    }

    /**
     * Inicializa la celda.
     */
    private void crearCelda() {
        this.getStyleClass().add(getNombre());
        this.setPrefSize(SIZE, SIZE);
    }

    /**
     * Obtiene el color de la celda en base a la variable boleana "blancas".
     */
    private String getNombre() {
        return (blancas) ? "blancas" : "negras";
    }

    /**
     * Establece el la nombreFigura y al jugador que pertenece a esta celda.
     * @param tipo
     * @param jugador
     */
    public void establecerFigura(String tipo, String jugador) {
        this.setJugador(jugador);
        this.nombreFigura.setValue(tipo);
        this.getStyleClass().add("activo");
    }

    private void crearEventos() {
        nombreFigura.addListener( (o, oldValue, newValue) -> pintarFigura() );
        this.setOnMouseClicked(e -> {
            if (nombreFigura != null || !nombreFigura.getValue().isEmpty())
                this.getStyleClass().add("presionado");
        });
        this.setOnMouseExited(e -> {
            if (nombreFigura != null || !nombreFigura.getValue().isEmpty())
                this.getStyleClass().add("no-presionado");
        });
    }

    private void pintarFigura() {
        String urlImagen = "imagenes/piezas/" + nombreFigura.getValue() + "-" + jugador + ".png";
        figura = new Image(urlImagen);
        Rectangle r = new Rectangle(SIZE-20, SIZE-20, new ImagePattern(figura));
        this.getChildren().add( r );
    }

    @Override
    public String toString() {
        return String.format("%d - %d \n", posY, posX);
    }

}
