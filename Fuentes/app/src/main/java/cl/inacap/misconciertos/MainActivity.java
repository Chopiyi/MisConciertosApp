package cl.inacap.misconciertos;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter1;
    EditText fechaEvento;


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
        final int year = Calendar.YEAR;
        final int month = Calendar.MONTH;
        final int day = Calendar.DAY_OF_MONTH;
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
    }

}