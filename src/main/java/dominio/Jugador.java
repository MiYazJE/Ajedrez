package dominio;

import java.util.ArrayList;

public class Jugador {

    private String nombre;
    private ArrayList<Celda> fichas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        this.fichas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Celda> getFichas() {
        return fichas;
    }

    public void setFichas(ArrayList<Celda> fichas) {
        this.fichas = fichas;
    }
}
