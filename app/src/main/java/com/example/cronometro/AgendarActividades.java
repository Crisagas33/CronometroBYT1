package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Button;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.Time;
import java.util.Calendar;

public class AgendarActividades extends AppCompatActivity implements View.OnClickListener {


    Button btnFecha, btnHora, btnAgregar1, btnCancelar1;
    EditText tv1, tv2, tv3, tv4;

    private int dia, mes, anio, hora, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendaractividades);

        btnFecha = (Button) findViewById(R.id.btnFecha);
        btnHora = (Button) findViewById(R.id.btnHora);
        tv2 = (EditText) findViewById(R.id.tv2); /// fecha
        tv3 = (EditText) findViewById(R.id.tv3); /// hora

        btnFecha.setOnClickListener(this);
        btnHora.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == btnFecha) {
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            anio = c.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                    tv2.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                }
            }
                    , dia, mes, anio);
            datePickerDialog.show();
        }
        if (view == btnHora) {
            final Calendar c = Calendar.getInstance();
            hora = c.get(Calendar.HOUR);
            minutos = c.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                    tv3.setText(hourOfDay + ":" + minute);
                }
            }, hora, minutos, false);
            timePickerDialog.show();
        }
    }

    public void Cronometro(View view){
        Intent cronometro = new Intent(this, ActivityCronometro.class);
        startActivity(cronometro);
    }
    public void Cancelar(View view){
        Intent cancelar = new Intent(this, HomeActivity.class);
        startActivity(cancelar);
    }
}
