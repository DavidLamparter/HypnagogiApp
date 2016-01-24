package none.HypnagogiApp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;

public class SleepScreen extends Activity implements SensorEventListener {

    MediaPlayer alertSound;
    Button stopSound;
    ImageButton startSound;
    SensorManager sensorMan;
    Sensor cowTip;
    Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep_screen);

        // setting up the accelerometer
        sensorMan = (SensorManager) getSystemService(SENSOR_SERVICE);
        cowTip = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorMan.registerListener(this, cowTip, SensorManager.SENSOR_DELAY_NORMAL);

        alertSound = MediaPlayer.create(this, R.raw.sure);
        stopSound = (Button) this.findViewById(R.id.stopB);
        stopSound.setVisibility(Button.GONE);

        startSound = (ImageButton) this.findViewById(R.id.startB);
        chrono = (Chronometer) this.findViewById(R.id.chrono);
        chrono.stop();

        startSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmActive = true;
               // startSound.setVisibility(Button.GONE);
                chrono.setBase(SystemClock.elapsedRealtime());
                chrono.start();
            }
        });


    }
    private boolean alarmActive = false;
    @Override
    public void onSensorChanged(SensorEvent event) {

        // test to see if phone has dropped in position in the y axis
        if(alarmActive) {
            if (event.values[1] < 4) {
                alertSound.start();
                stopSound.setVisibility(Button.VISIBLE);
                chrono.stop();
                alarmActive = false;
            }
        }

        stopSound.setText("Stop Sound");

        stopSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertSound.pause();
                stopSound.setVisibility(Button.GONE);
                //startSound.setVisibility(Button.VISIBLE);

            }
        });



    }

    @Override
    public void onBackPressed() {
        alarmActive = false;
        alertSound.stop();
        super.onBackPressed();
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}