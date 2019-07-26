package com.gunar.memoria;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> asks = new LinkedList<String>();
    Chronometer crono;

    Button uno, dos, tres, cuatro, cinco, seis, siete, ocho, iniciar, reiniciar;

    TextView mensaje;

    boolean inicio;

    int queCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crono = (Chronometer) findViewById(R.id.cronometro);

        mensaje = (TextView) findViewById(R.id.textView);

        uno = (Button) findViewById(R.id.imageButton1);
        dos = (Button) findViewById(R.id.imageButton2);
        tres = (Button) findViewById(R.id.imageButton3);
        cuatro = (Button) findViewById(R.id.imageButton4);
        cinco = (Button) findViewById(R.id.imageButton5);
        seis = (Button) findViewById(R.id.imageButton6);
        siete = (Button) findViewById(R.id.imageButton7);
        ocho = (Button) findViewById(R.id.imageButton8);

        iniciar = (Button) findViewById(R.id.button);
        reiniciar = (Button) findViewById(R.id.button2);

        inicio = false;

        queCarta = 0;

        asks.add("Pareja 1");
        asks.add("Pareja 1");
        asks.add("Pareja 2");
        asks.add("Pareja 2");
        asks.add("Pareja 3");
        asks.add("Pareja 3");
        asks.add("Pareja 4");
        asks.add("Pareja 4");

        crono.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long systemCurrTime = SystemClock.elapsedRealtime();
                long chronometerBaseTime = chronometer.getBase();
                long deltaTime = systemCurrTime - chronometerBaseTime;

                if (deltaTime > 30000) {
                    chronometer.stop();
                    mensaje.setText("Se acabo el tiempo");
                    reiniciar(null);
                }
            }
        });

        cantidadPares = 0;
        cartaActual = -1;

//        Toast.makeText(this, asks.toString(), Toast.LENGTH_LONG).show();
    }


    public void iniciar(View view) {
        crono.setBase(SystemClock.elapsedRealtime());
        crono.start();
        inicio = true;

        view.setEnabled(false);
        reiniciar.setEnabled(true);

        Collections.shuffle(asks);
//        Toast.makeText(this, asks.toString(), Toast.LENGTH_LONG).show();
    }

    public void reiniciar(View view) {
        crono.stop();
        long systemCurrTime = SystemClock.elapsedRealtime();
        crono.setBase(systemCurrTime);

        reiniciar.setEnabled(false);
        iniciar.setEnabled(true);

        inicio = false;
        queCarta = 0;

        mensaje.setText("");

//        Collections.shuffle(asks);
        uno.setBackgroundResource(R.drawable.fondo);
        uno.setText("");
        uno.setEnabled(true);
        dos.setBackgroundResource(R.drawable.fondo);
        dos.setText("");
        dos.setEnabled(true);
        tres.setBackgroundResource(R.drawable.fondo);
        tres.setText("");
        tres.setEnabled(true);
        cuatro.setBackgroundResource(R.drawable.fondo);
        cuatro.setText("");
        cuatro.setEnabled(true);
        cinco.setBackgroundResource(R.drawable.fondo);
        cinco.setText("");
        cinco.setEnabled(true);
        seis.setBackgroundResource(R.drawable.fondo);
        seis.setText("");
        seis.setEnabled(true);
        siete.setBackgroundResource(R.drawable.fondo);
        siete.setText("");
        siete.setEnabled(true);
        ocho.setBackgroundResource(R.drawable.fondo);
        ocho.setText("");
        ocho.setEnabled(true);

    }

    int cartaActual, cantidadPares;
    Button primeraCarta;

    public void carta(final View view) {
        String id = getResources().getResourceEntryName(view.getId());
        int num = Integer.parseInt(id.substring(id.length() - 1, id.length())) - 1;
        if (inicio && cartaActual != num){

            queCarta++;

            Toast.makeText(this, num + "", Toast.LENGTH_LONG).show();

            view.setBackgroundResource(R.drawable.blanco);
            ((Button) view).setText(asks.get(num));

            if (queCarta == 1) {
                cartaActual = num;
                primeraCarta = (Button) view;
            } else if (queCarta == 2) {
                if (asks.get(cartaActual).equals(asks.get(num))) {
                    cantidadPares++;
                    primeraCarta.setEnabled(false);
                    view.setEnabled(false);
                } else {
                    ocultar(primeraCarta, (Button) view);
                }
                cartaActual = -1;
                queCarta = 0;
            }

            if (cantidadPares == 4) {
                cantidadPares = 0;
                mensaje.setText("Gano");
                crono.stop();
            }
        }
    }

    public void ocultar(final Button primera, final Button segunda){
        new Handler().postDelayed(new Runnable() {

            public void run() {

                primera.setBackgroundResource(R.drawable.fondo);
                primera.setText("");

                segunda.setBackgroundResource(R.drawable.fondo);
                segunda.setText("");

            }
        }, 800);
    }
}
