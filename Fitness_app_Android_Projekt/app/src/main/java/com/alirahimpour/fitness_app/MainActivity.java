package com.alirahimpour.fitness_app;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.SystemClock;
import android.os.Handler;


import java.util.Locale;
import static com.alirahimpour.fitness_app.R.string._00_00_00;
import static java.util.Locale.*;


public class MainActivity extends Activity implements SensorEventListener {


    //stowa...........................

    TextView textView ;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

    //sensor...........................

    private SensorManager sm;
    private Sensor stepCounter;

    //App .............................

    private Button startStopBtn;
    private Button resetBtn;
    private TextView meterDisplay, kalDisplay, stepDisplay;
    private int currentSteps = 0;
    private int initialSteps = 0;

    private boolean isCounting = false;




    /* XXXXXXXXXXXXXXXXXXXXXX  ON Create XXXXXXXXXXXXXXXXXXX*/
    //The Activity Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* die elemente im Layout implementieren die Elemente,
         * die man in Layout angelegt hat müssen hier anhand
         * ihrer IDs zugewisen werden
        */

        startStopBtn = findViewById(R.id.startStopBtn);
        resetBtn =  findViewById(R.id.resetBtn);

        kalDisplay =  findViewById(R.id.kalDisplay);
        meterDisplay =  findViewById(R.id.meterDisplay);
        stepDisplay =  findViewById(R.id.stepDisplay);



        /* dem SensorManager sm wird sensor service zugewiesen und die
         * Variable StepConter bekommt zugrief auf stepCounter sensor und
         * die Events
        */
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        if (sm != null) {
            stepCounter = sm.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        }


        /*
        stowatch..................................
        */
        textView = findViewById(R.id.textView_stowa);
        handler = new Handler() ;

    }



    /* hier wird einen Listener für einen Sensor registriert. sobald die App in vordergrund steht*/
    protected void onResume(){
        super.onResume();
        sm.registerListener(this, stepCounter, SensorManager.SENSOR_DELAY_UI);
    }

    /* register wird aufgehoben sobald die App im hintergrund gerückt wird. */
    @Override
    protected void onPause() {
        super.onPause();
        sm.unregisterListener(this);
    }




    /*XXXXXXXXXXXXXXXXX Sensor XXXXXXXXXXXXXXXXXXXXXXXXXXX*/
    /* in onSensorChanged() werden die änderungen des Sensorwerte
     * vermittelt und dierichtige Anzahl an Schritte, die man gelaufen
     * ist über zwei Hilfsvariablenberechnet. Danach werden die Entfernung
     * und Kalorie Werte durch das Aufrufen von der Methode updateKalMeter();
     * aktualisiert.
     * durch !isCounting verbindet man den Stopp/Start Butoon und Sensor
    */
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        if (!isCounting){
            return;
        }
        if (initialSteps == 0){
            initialSteps = (int)sensorEvent.values[0];
        }else{
            currentSteps = (int)sensorEvent.values[0] - initialSteps;
        }

        stepDisplay.setText(String.format(US,"%d", currentSteps));
        updateKalMeter();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }




    /* die Entfernung und Kalorie Werte werden hier berechnet
     * die Einstellungsdaten werden über sharedPreferences
     * übermittelt*/
    public void updateKalMeter() {

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int schritt = Integer.valueOf(sharedPreferences.getString("Schrittlänge", "70"));
        double schrittlaenge = schritt / 100.0;
        int meter = (int)(currentSteps * schrittlaenge);

        meterDisplay.setText(String.format(US,"%d m", meter));

        // 320cal auf 1000m
        int kalPerKm = Integer.valueOf(sharedPreferences.getString("Kalorien", "320"));
        int kalorien = (int)((meter /1000.0)* kalPerKm);
        kalDisplay.setText( String.format(US,"%d Kal", kalorien));

    }




    /*xxxxxxxxxxxxxxxxxxxxxxxxxxx(Start/Stop, reset, usw...)xxxxxxxxxxxxxxxxx*/
    /* durch Reset button wird fast alles zurückgesetzt*/

    public void reset(View view){

        stepDisplay.setText("0");
        isCounting = false;
        switchBtn();

        initialSteps = 0;
        currentSteps = 0;

        /* stoppuhr-reset */
        MillisecondTime = 0L ;
        StartTime = 0L ;
        TimeBuff = 0L ;
        UpdateTime = 0L ;
        Seconds = 0 ;
        Minutes = 0 ;
        MilliSeconds = 0 ;

        textView.setText(getString(_00_00_00));

    }

    /* Start/Stopp Button */
    public void startStop(View view){

        /*
        true => !true => false
        false => !false => true
        */
        isCounting = !isCounting; //hier ist die verbindung zu Sensor

        switchBtn();


    }


    /* Jedesmal wenn man auf dem knopf Start/Stopp druckkt
     * ändert sich den Text auf dem Knopf und startet oder
     * Stoppt man die Stoppuhr */
    public void switchBtn(){
        if (isCounting){
            startStopBtn.setText(R.string.switchStop);

            /* StoppUhr-Start */
            StartTime = SystemClock.uptimeMillis();
            handler.postDelayed(runnable, 0);


        }else{
            startStopBtn.setText(R.string.switchStart);

            /* stoppUhr-Pause */
            TimeBuff += MillisecondTime;
            handler.removeCallbacks(runnable);

        }

    }




    /* um bei der Rotation Verlust von Daten zu verhindern
     * verwendet man diese zwei Methoden.
    */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentSteps", currentSteps);
        outState.putInt("initialSteps", initialSteps);
        outState.putBoolean("isCounting", isCounting);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        currentSteps = savedInstanceState.getInt("currentSteps");
        initialSteps = savedInstanceState.getInt("initialSteps");
        isCounting = savedInstanceState.getBoolean("isCounting");

        switchBtn();

        super.onRestoreInstanceState(savedInstanceState);
    }




    /* xxxxxxxxxxxxxxxxx Menu xxxxxxxxxxxxxxxxx */

    /* onCreateOptionsMenu:Initialisiert die Menüelemente anhand
     * MenuInflater.
     *
     * onOptionsItemSelected:Es wird aufgerufen, wenn ein Element
     * im Optionsmenü ausgewählt ist.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.settingsItem){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }

        return true;
    }




    /* -----------------------------Run StoWatch------------------------------- */

    public Runnable runnable = new Runnable() {

        @SuppressLint("SetTextI18n")
        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            textView.setText(String.format(Locale.GERMANY, "%d%s%s%s%s", Minutes, getString(R.string.Colon), String.format(Locale.GERMANY, "%02d", Seconds), getString(R.string.Colon), String.format(Locale.GERMANY, "%03d", MilliSeconds)));


            handler.postDelayed(this, 0);
        }

    };

}



