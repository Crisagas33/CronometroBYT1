package com.example.cronometro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActivityCronometro extends AppCompatActivity implements View.OnClickListener {

    Button btnStart, btnStop, btnReset;
    boolean isOn = false;
    TextView textView2;
    Thread textView2s;
    int mil = 0, seg = 0, minutos = 0;
    Handler h = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cronometro);

        btnStart = findViewById(R.id.btnStart);
        btnReset = findViewById(R.id.btnReset);
        btnStop  = findViewById(R.id.btnStop);

        textView2 = findViewById(R.id.textView2);

        btnStart.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        textView2s = new Thread(new Runnable() {
            @Override
            public void run() {
                while ( true ) {
                    if (isOn) {
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mil++;
                        if (mil == 999) {
                            seg++;
                            mil = 0;
                        }
                        if (seg == 59) {
                            minutos++;
                            seg = 0;
                        }
                        h.post(new Runnable() {
                            @Override
                            public void run() {
                                String m, s = "", mi;
                                if (mil < 10) {
                                    m = "00" + mil;
                                } else if (mil < 100) {
                                    m = "0" + mil;
                                } else {
                                    m = "" + mil;
                                }
                                if (seg < 10) {
                                    s = "0" + seg;
                                } else {
                                    s = "" +seg;
                                }
                                if (minutos < 10) {
                                    mi = "0" + minutos;
                                } else {
                                    mi = "" + minutos;
                                }
                                textView2.setText(mi+":"+s+":"+m);
                            }
                        });
                    }
                }
            }
        });
        textView2s.start();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnStart:
                isOn = true;
                break;
            case  R.id.btnStop:
                isOn = false;
                break;
            case R.id.btnReset:
                isOn = false;
                mil     = 0;
                seg     = 0;
                minutos = 0;
                textView2.setText("00:00:000");
        }
    }

    public void Siguiente(View view) {
        Intent siguiente = new Intent(this, HomeActivity.class);
        startActivity(siguiente);
    }
}