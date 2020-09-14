package cl.inacap.misconciertos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import cl.inacap.misconciertos.dao.EventosDAO;
import cl.inacap.misconciertos.dto.Evento;

public class Adaptador extends BaseAdapter {

    private EventosDAO eventosDAO = new EventosDAO();
    private Context contexto;

    public Adaptador(Context contexto, EventosDAO eventosDAO) {
        this.eventosDAO = eventosDAO;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return eventosDAO.getAll().size();
    }

    @Override
    public Object getItem(int i) {
        return eventosDAO.getAll().get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Evento evento = (Evento) getItem(i);
        view = LayoutInflater.from(contexto).inflate(R.layout.base_lista_evento, null);
        ImageView icono = view.findViewById(R.id.iconoCalificacion);
        TextView artista = view.findViewById(R.id.mostrar_eventos);
        icono.setImageResource(evento.getIcono());
        artista.setText("-Artista: " + evento.getArtista() + "\n" + "-Fecha: " + evento.getFecha() + "\n" + "-Precio: " + evento.getEntrada());
        return view;
    }
}
