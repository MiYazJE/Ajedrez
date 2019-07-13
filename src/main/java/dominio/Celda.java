/**
 * @author Rubén Saiz
 */
package dominio;

import dominio.figuras.*;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Celda extends StackPane implements PropiedadesCelda {

    private IntegerProperty tipoFigura;
    private Figura figura;
    private boolean blancas;
    public int posY;
    public int posX;
    private String jugador;
    private Circle circulo;
    private boolean posible;

    // En esta lista se almacenan las celdas en las cuales se han hecho predicciones anteriores
    private static ArrayList<Celda> predicciones;
    // Aquí sera almacenada la celda que haya sido presionada
    private static Celda presionada;

    public Celda(int blanca, int posY, int posX) {
        tipoFigura = new SimpleIntegerProperty();
        this.blancas = (blanca % 2 == 0);
        crearPropiedadesCelda();
        crearEventos();
        crearCirculo();
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

    private void crearCirculo() {
        circulo = new Circle();
        circulo.setCenterX(SIZE / 4);
        circulo.setCenterY(SIZE / 4);
        circulo.setRadius(SIZE / 8);
    }

    /**
     * Crea las propiedades:
     *  -Tamaños de la celda.
     *  -Añade una clase de estilos css.
     */
    private void crearPropiedadesCelda() {
        this.getStyleClass().add(getColor());
        this.setPrefSize(SIZE, SIZE);
    }

    /**
     * Obtiene el color de la celda en base a la variable boleana "blancas",
     * el string obtenido es usado como una clase css.
     */
    private String getColor() {
        return (blancas) ? "blancas" : "negras";
    }

    /**
     * Establece el tipoFigura al jugador que pertenece a esta celda.
     * @param figura
     * @param jugador
     */
    public void establecerFigura(Integer figura, String jugador) {
        this.setJugador(jugador);
        this.tipoFigura.setValue(figura);
        this.figura = getFigura();
        this.getStyleClass().add("activo");
    }

    private Figura getFigura() {
        switch (tipoFigura.getValue()) {
            case CABALLO: return new Caballo();
            case ALFIL:   return new Alfil();
            case REINA:   return new Reina();
            case REY:     return new Rey();
            case TORRE:   return new Torre();
        }
        return new Peon();
    }

    private void crearEventos() {
        tipoFigura.addListener( (o, oldValue, newValue) -> pintarFigura() );
        this.setOnMouseClicked(e -> {
            if (tipoFigura.getValue() != 0) {
               aplicarBrillo();
               crearPredicciones();
            }
            else if (tipoFigura.getValue() == 0 && presionada != null) {
                intercambiarCeldas();
            }
        });
    }

    private void aplicarBrillo() {
        ColorAdjust effect = new ColorAdjust();
        effect.setBrightness(0.5);
        this.setEffect(effect);
    }

    private void pintarFigura() {
        if (this.tipoFigura.getValue() == 0) return;
        String player = (jugador == null) ? presionada.jugador : jugador;
        String urlImagen = "imagenes/piezas/" + FIGURAS[tipoFigura.getValue()] + "-" + player + ".png";
        Image imagenFigura = new Image( urlImagen );
        Rectangle r = new Rectangle(SIZE-20, SIZE-20, new ImagePattern( imagenFigura ));
        this.getChildren().add( r );
    }

    /**
     * Al darle click sobre una celda se mostrara graficamente con un circulo sobre las celdas a las cuales puedes moverte.
     */
    private void crearPredicciones() {
        limpiarPredicciones();
        Celda celdaActual = Tablero.celdas[posY][posX];
        predicciones = figura.crearPredicciones(Tablero.celdas, celdaActual);
        for (Celda celda : predicciones)  celda.posible = true;
    }

    /**
     * Limpia la lista sobre la que teniamos las predicciones almacendadas,
     * asi cuando se presione sobre otra celda diferente se muestren sus
     * predicciones y los anteriores sean eliminados.
     */
    private void limpiarPredicciones() {

        if (presionada != null && this != presionada)
            presionada.setEffect(null);

        presionada = this;

        for (Celda celda : predicciones)
            limpiarCelda(celda);

        predicciones.clear();
    }

    private void intercambiarCeldas() {
        if (this.posible){
            // Quitar el indicador de movimiento
            this.getChildren().clear();
            this.tipoFigura.setValue(presionada.tipoFigura.getValue());
            this.jugador = presionada.jugador;
            this.figura = presionada.figura;

            presionada.tipoFigura.setValue(0);
            presionada.getChildren().clear();
            presionada.getStyleClass().remove("activo");
            presionada.setEffect(null);
            presionada.jugador = null;
            presionada.posible = false;

            limpiarPredicciones();
        }
    }

    /**
     * Elimina todos los componentes hijos de una celda, como pueden ser
     * un circulo en el caso de una celda prediccion o una tipoFigura en el de
     * ser una ficha.
     * @param celda
     */
    private void limpiarCelda(Celda celda) {
        if (celda.jugador == null) {
            Tablero.celdas[celda.posY][celda.posX].getChildren().clear();
            Tablero.celdas[celda.posY][celda.posX].getStyleClass().remove("activo");
            celda.posible = false;
        }
    }

    /**
     * Valida si la celda contiene alguna tipoFigura.
     * Si contiene algún valor diferente a 0 significa que no es válida.
     * @param celda
     * @return true: si contiene tipoFigura  false: no cotiene tipoFigura
     */
    public static boolean esValida(Celda celda) {
        return celda.tipoFigura.getValue() == 0;
    }

    /**
     * Introduce un circulo en medio de la celda.
     */
    public void pintarPosibleMovimiento() {
        this.getChildren().add( circulo );
        this.getStyleClass().add("activo");
    }

    @Override
    public String toString() {
        return String.format("%d - %d \n", posY, posX);
    }

}
