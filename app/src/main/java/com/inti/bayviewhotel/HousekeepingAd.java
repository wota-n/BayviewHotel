package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HousekeepingAd extends AppCompatActivity {

    EditText type_input, status_input, staff_input;
    Button add_button;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_expand);

        type_input = findViewById(R.id.type_input);
        status_input = findViewById(R.id.status_input);
        staff_input = findViewById(R.id.staff_input);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                MyDatabaseHelper2 myDB2 = new MyDatabaseHelper2(HousekeepingAd.this);
                myDB2.addRoom(type_input.getText().toString().trim(),
                        status_input.getText().toString().trim(),
                        staff_input.getText().toString().trim());
            }
        });
    }

    public void openMainActivity() {
        Intent intent = new Intent(this, HousekeepingPortal.class);
        startActivity(intent);
    }
}

