package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HousekeepingAssign extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_update);
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, HousekeepingPortal.class);
        startActivity(intent);
    }
}
