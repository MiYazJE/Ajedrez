/**
 * @author Rubén Saiz
 */
package dominio;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
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
    private Circle circulo;
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
        crearCirculo();
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

    public String getNombre() {
        return this.jugador;
    }

    private void crearCirculo() {
        circulo = new Circle();
        circulo.setCenterX(SIZE/4);
        circulo.setCenterY(SIZE/4);
        circulo.setRadius(SIZE/8);
    }

    /**
     * Crea las propiedades:
     *  -Tamaños de la celda.
     *  -
     */
    private void crearCelda() {
        this.getStyleClass().add(getColor());
        this.setPrefSize(SIZE, SIZE);
    }

    /**
     * Obtiene el color de la celda en base a la variable boleana "blancas".
     */
    private String getColor() {
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
        Image imagenFigura = new Image(urlImagen);
        Rectangle r = new Rectangle(SIZE-20, SIZE-20, new ImagePattern(imagenFigura));
        this.getChildren().add( r );
    }

    /**
     * Al darle click sobre una celda se mostrara graficamente con un circulo sobre las celdas a las cuales puedes moverte.
     */
    private void crearPredicciones() {

        limpiarPredicciones();
        Celda celdaActual = Tablero.celdas.get(posY).get(posX);
        presionada = celdaActual;

        if (figura.getValue() == PEON) {
            if (celdaActual.getJugador().equals("j1")) {
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
            moverCaballo( celdaActual );
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

    /**
     * Limpia la lista sobre la que teniamos las predicciones almacendadas,
     * asi cuando se presione sobre otra celda diferente se muestren sus
     * predicciones y los anteriores sean eliminados.
     */
    private void limpiarPredicciones() {

        if (presionada != null )
            presionada.getStyleClass().add("no-presionado");

        for (Celda celda : predicciones)
            limpiarCelda(celda);

        predicciones.clear();
    }

    /**
     * Elimina todos los componentes hijos de una celda, como pueden ser
     * un circulo en el caso de una celda prediccion o una figura en el de
     * ser una ficha.
     * @param celda
     */
    private void limpiarCelda(Celda celda) {
        celda.getChildren().clear();
    }

    /**
     * Valida si la celda contiene alguna figura.
     * Si contiene algún valor diferente a 0 significa que no es válida.
     * @param celda
     * @return true: si contiene figura  false: no cotiene figura
     */
    private boolean esValida(Celda celda) {
        return celda.figura.getValue() == 0;
    }

    /**
     * Introduce un circulo en medio de la celda.
     */
    private void pintarPosibleMovimiento() {
        this.getChildren().add( circulo );
    }

    private void moverCaballo(Celda celda) {
        /*

        0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0
        0 0 0 0 0 0 0 0
        0 0 * 0 * 0 0 0
        0 * 0 0 0 * 0 0
        0 0 0 X 0 0 0 0
        0 * 0 0 0 * 0 0
        0 0 * 0 * 0 0 0

        ROW -> [-2, -1, -2, -1,  1,  2, 2, 1]
        COL -> [-1, -2,  1,  2, -2, -1, 1, 2]

         */

        int[] rowDirections = {-2, -1, -2, -1,  1,  2, 2, 1};
        int[] colDirections = {-1, -2,  1,  2, -2, -1, 1, 2};
        int posF, posC;

        for (int i = 0; i < 8; i++) {
            posF = posY + rowDirections[i];
            posC = posX + colDirections[i];
            if (posF < 0 || posF >= 8 || posC < 0 || posC >= 8)
                continue;
            Celda celdaActual = Tablero.celdas.get(posF).get(posC);
            if (esValida( celdaActual )) {
                celdaActual.pintarPosibleMovimiento();
                predicciones.add( celdaActual );
            }
        }

    }

    @Override
    public String toString() {
        return String.format("%d - %d \n", posY, posX);
    }

}
