/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Celda extends StackPane implements PropiedadesCelda {

    private IntegerProperty figura;
    private boolean blancas;
    private int posY;
    private int posX;
    private String jugador;
    private Image imagenFigura;
    // En esta lista se almacenan las celdas en las cuales se han hecho predicciones anteriores
    private static ArrayList<Celda> predicciones;
    private static Celda presionada;

    public Celda(int blanca, int posY, int posX) {
        figura = new SimpleIntegerProperty();
        this.blancas = (blanca % 2 == 0);
        crearCelda();
        crearEventos();
        this.posY = posY;
        this.posX = posX;
        predicciones = new ArrayList<>();
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getJugador() {
        return jugador;
    }

    public void setFigura(Integer figura) {
        this.figura.set(figura);
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
     * Establece la figura al jugador que pertenece a esta celda.
     * @param figura
     * @param jugador
     */
    public void establecerFigura(Integer figura, String jugador) {
        this.setJugador(jugador);
        this.figura.setValue(figura);
        this.getStyleClass().add("activo");
    }

    private void crearEventos() {
        figura.addListener( (o, oldValue, newValue) -> pintarFigura() );
        this.setOnMouseClicked(e -> {
            if (figura.getValue() != 0) {
                this.getStyleClass().add("presionado");
                crearPredicciones();
            }
        });
    }

    private void pintarFigura() {
        String urlImagen = "imagenes/piezas/" + FIGURAS[figura.getValue()] + "-" + jugador + ".png";
        imagenFigura = new Image(urlImagen);
        Rectangle r = new Rectangle(SIZE-20, SIZE-20, new ImagePattern(imagenFigura));
        this.getChildren().add( r );
    }

    private void crearPredicciones() {

        limpiarPredicciones();
        Celda aux = Tablero.celdas.get(posY).get(posX);
        presionada = aux;

        if (figura.getValue() == PEON) {
            if (aux.getJugador().equals("j1")) {
                if (posY == 0) return;
                Tablero.celdas.get(posY-1).get(posX).pintarPosibleMovimiento();
                predicciones.add(Tablero.celdas.get(posY-1).get(posX));
            }
            else {
                if (posY == 0) return;
                Tablero.celdas.get(posY+1).get(posX).pintarPosibleMovimiento();
                predicciones.add(Tablero.celdas.get(posY+1).get(posX));
            }
        }
        else if (figura.getValue() == CABALLO) {

        }
        else if (figura.getValue() == TORRE) {

        }
        else if (figura.getValue() == ALFIL) {

        }
        else if (figura.getValue() == REINA) {

        }
        else if (figura.getValue() == REY) {

        }

    }

    private void limpiarPredicciones() {
        if (presionada != null )
            presionada.getStyleClass().add("no-presionado");
        for (Celda celda : predicciones) {
            celda.getChildren().clear();
        }
        predicciones.clear();
    }

    public void pintarPosibleMovimiento() {
        Circle circulo = new Circle();
        circulo.setCenterX(SIZE/4);
        circulo.setCenterY(SIZE/4);
        circulo.setRadius(SIZE/8);
        this.getChildren().add( circulo );
    }

    @Override
    public String toString() {
        return String.format("%d - %d \n", posY, posX);
    }

}
