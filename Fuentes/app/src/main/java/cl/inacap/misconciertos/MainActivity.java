package cl.inacap.misconciertos;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import cl.inacap.misconciertos.dao.EventosDAO;
import cl.inacap.misconciertos.dto.Evento;

public class MainActivity extends AppCompatActivity {


    private Spinner spinner;
    private Spinner spinner2;
    private ArrayAdapter<CharSequence> adapter;
    private ArrayAdapter<CharSequence> adapter1;
    private EditText fechaEvento;
    private EditText nombreArtista;
    private EditText valorEntradaTxt;
    private Spinner genero;
    private Spinner calificacionSelect;
    private EditText fechaTxt;
    private Button botonRegistrar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.spinner = (Spinner) findViewById(R.id.selectorGenero);
        this.spinner2 = (Spinner) findViewById(R.id.selectorCalificacion);
        this.adapter =ArrayAdapter.createFromResource(this, R.array.arrayGeneros, android.R.layout.simple_spinner_item);
        this.adapter1 = ArrayAdapter.createFromResource(this, R.array.arrayClasificacion, android.R.layout.simple_spinner_item);
        this.fechaEvento = findViewById(R.id.fechaEvento);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter1);
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.YEAR;
        final int month = calendar.MONTH;
        final int day = calendar.DAY_OF_MONTH;
        fechaEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month +1;
                        String date = day + "/" + month + "/" + year;
                        fechaEvento.setText(date);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        this.nombreArtista = findViewById(R.id.nombreArtista);
        this.valorEntradaTxt = findViewById(R.id.valorEntrada);
        this.fechaTxt = findViewById(R.id.fechaEvento);
        this.genero = findViewById(R.id.selectorGenero);
        this.calificacionSelect = (findViewById(R.id.selectorCalificacion));
        this.botonRegistrar = findViewById(R.id.botonRegistro);
        this.botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventosDAO eventosDAO = new EventosDAO();
                ListView listaEventos;
                Adaptador adaptador;
                List<String> errores = new ArrayList<String>();
                if(nombreArtista.getText().toString().isEmpty()){
                    errores.add("No ingresó el nombre del artista");
                }
                if(fechaTxt.getText().toString().isEmpty()){
                    errores.add("No seleccionó la fecha");
                }
                if(genero.getSelectedItem().toString().isEmpty()){
                    errores.add("No seleccionó el género musical");
                }
                int calificacion = Integer.parseInt(calificacionSelect.getSelectedItem().toString());
                int valorEntrada = 0;
                try {
                    valorEntrada = Integer.parseInt(valorEntradaTxt.getText().toString());
                    if(valorEntrada <= 0){
                        throw new NumberFormatException();
                    }
                }catch (NumberFormatException e){
                    errores.add("El valor de la entrada debe ser mayor que 0");
                }
                if(errores.isEmpty()){
                    Evento evento = new Evento();
                    evento.setArtista(nombreArtista.getText().toString());
                    evento.setFecha(fechaTxt.getText().toString());
                    evento.setGenero(genero.getSelectedItem().toString());
                    evento.setEntrada(valorEntrada);
                    evento.setCalificacion(calificacion);
                    if(evento.getCalificacion() >= 1 && evento.getCalificacion() <= 3){
                        evento.setIcono(R.drawable.malo);
                    } else if (evento.getCalificacion() ==4 || evento.getCalificacion() ==5 ){
                        evento.setIcono(R.drawable.tranquilo);
                    } else {
                        evento.setIcono(R.drawable.sonreir);
                    }
                    eventosDAO.add(evento);
                    listaEventos = findViewById(R.id.viewEventos);
                    adaptador = new Adaptador(MainActivity.this, eventosDAO);
                    listaEventos.setAdapter(adaptador);
                    listaEventos.setVisibility(View.VISIBLE);
                    Toast.makeText(MainActivity.this, "Ingreso exitoso", Toast.LENGTH_LONG).show();
                } else {
                    String mensaje="";
                    for (String e: errores) {
                        mensaje+="-" + e + "\n";
                    }
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
                    alertBuilder.setTitle("No ha sido posible registrar").setMessage(mensaje).setPositiveButton("Aceptar", null).create().show();
                }
            }
        });


    }

}