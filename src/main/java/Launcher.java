import dominio.Juego;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Launcher extends Application {

    @Override
    public void start(Stage stage) {

        Juego ajedrez = new Juego();
        Scene scene = new Scene( ajedrez );

        stage.setScene( scene );
        stage.setResizable( false );
        stage.setTitle( "Ajedrez By Rubén Saiz" );
        stage.getIcons().add(new Image("imagenes/icon.png"));
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
