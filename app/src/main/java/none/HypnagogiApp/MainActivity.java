package none.HypnagogiApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button sleepScreen;
    Button howTo;
    Button about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sleepScreen = (Button) this.findViewById(R.id.sleepB);
        howTo = (Button) this.findViewById(R.id.howToB);
        about = (Button) this.findViewById(R.id.aboutB);

        sleepScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SleepScreen.class));
            }
        });

        howTo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HowToUse.class));
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), About.class));
            }
        });




    }
}


