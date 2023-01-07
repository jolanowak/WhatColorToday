package com.example.whateattoday;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String[] MORNING_COLOR = {
            "morning_color1", "morning_color2", "morning_color3"
    };

    private static final String[] DAY_COLOR = {
            "day_color1", "day_color2", "day_color3"
    };

    private static final String[] EVENING_COLOR = {
            "evening_color1", "evening_color2", "evening_color3"
    };



    private ImageView imageView;
    private TextView textView;
    private Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);
        random = new Random();

        TextView textView2 = findViewById(R.id.textView2);
        imageView.setImageResource(R.drawable.hello);
        textView.setText("Zainspiruj się:)");
        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.nice);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textView2.setVisibility(View.INVISIBLE);
                mediaPlayer.start();
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR_OF_DAY);

                String[] images;
                String label;

                if (hour >= 6 && hour < 12) {
                    images = MORNING_COLOR;
                    label = "Kolor słonecznego poranka";
                } else if (hour >= 12 && hour < 15) {
                    images = DAY_COLOR;
                    label = "Kolor pięknego dnia";
                } else if (hour >= 15 && hour < 24) {
                    images = EVENING_COLOR;
                    label = "Kolor gwieździstego wieczoru";
                } else {
                    textView.setText("To czas na sen, a nie na malowanie");
                    return;
                }

                int index = random.nextInt(images.length);
                String imageName = images[index];
                int resourceId = getResources().getIdentifier(imageName, "drawable", getPackageName());
                imageView.setImageResource(resourceId);

                textView.setText(label);


            }
        });
    }
}