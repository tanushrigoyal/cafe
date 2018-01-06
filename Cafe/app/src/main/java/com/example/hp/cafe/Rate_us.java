package com.example.hp.cafe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

public class Rate_us extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_us);
        final RatingBar RB=(RatingBar)findViewById(R.id.ratingBar);
        Button B1=(Button)findViewById(R.id.submit_button);
        final TextView Display_ratting = (TextView)findViewById(R.id.rating_display);

        B1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Display_ratting.setText("Your rating is : "+ RB.getRating());

            }
        });
    }
}

