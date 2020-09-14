package cl.inacap.misconciertos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.misconciertos.dto.Evento;

public class EventosDAO {

    private static List<Evento> listaEventos = new ArrayList<Evento>();

    public void add(Evento e){
        listaEventos.add(e);
    }

    public void remove(Evento e){
        listaEventos.remove(e);
    }

    public void removeAll(){
        listaEventos.clear();
    }

    public List<Evento> getAll(){
        return listaEventos;
    }

}
