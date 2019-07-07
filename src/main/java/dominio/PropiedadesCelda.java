package dominio;

public interface PropiedadesCelda {

    public static final int SIZE = 85;

    public static int PEON    = 1;
    public static int TORRE   = 2;
    public static int CABALLO = 3;
    public static int ALFIL   = 4;
    public static int REINA   = 5;
    public static int REY     = 6;

    public static final String[] FIGURAS = {
            "", "peon", "torre", "caballo", "alfil", "reina", "rey"
    };

}
