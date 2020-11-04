package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HousekeepingPortal extends AppCompatActivity implements View.OnClickListener{

    private Button button;
    private ImageButton imageb1;
    private Button button3;
    private ImageButton imageb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_portal);

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMainActivity2();
            }
        });

        imageb1 =(ImageButton) findViewById(R.id.imageb1);
        imageb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openMainActivity2();
            }
        });

        imageb3 =(ImageButton) findViewById(R.id.imageb3);
        imageb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openMainActivity4();
            }
        });

        button3 =(Button) findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                openMainActivity4();
            }
        });
    }
    public void openMainActivity2() {
        Intent intent = new Intent(this, HousekeepingRoomList.class);
        startActivity(intent);
    }
    public void openMainActivity3() {
        Intent intent = new Intent(this, HousekeepingAssign.class);
        startActivity(intent);
    }
    public void openMainActivity4() {
        Intent intent = new Intent(this, HousekeepingAd.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }
}