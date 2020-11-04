package com.inti.bayviewhotel;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HousekeepingUpdate extends AppCompatActivity {

    EditText room_input, status_input, staff_input;
    Button update_button2, delete_button2;

    String id, type, status, staff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_update);

        room_input = findViewById(R.id.room_input2);
        status_input = findViewById(R.id.status_input2);
        staff_input = findViewById(R.id.staff_input2);
        update_button2 = findViewById(R.id.update_button2);
        delete_button2 = findViewById(R.id.delete_button2);
        getAndSetIntentData2();

        ActionBar ab = getSupportActionBar();
        if(ab != null) {
            ab.setTitle("ROOM " + id);
        }

        update_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(HousekeepingUpdate.this);
                type = room_input.getText().toString().trim();
                status = status_input.getText().toString().trim();
                staff = staff_input.getText().toString().trim();
                myDB.updateData2(id, type, status, staff);
            }
        });
        delete_button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                confirmDialog2();
            }
        });

    }
    void getAndSetIntentData2(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") &&
                getIntent().hasExtra("status") && getIntent().hasExtra("staff")){
            id = getIntent().getStringExtra("id");
            type = getIntent().getStringExtra("type");
            status = getIntent().getStringExtra("status");
            staff = getIntent().getStringExtra("staff");

            room_input.setText(type);
            status_input.setText(status);
            staff_input.setText(staff);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
    void confirmDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete Room " + id + " ?");
        builder.setMessage("Are you sure you want to delete Room" + id + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper2 myDB = new MyDatabaseHelper2(HousekeepingUpdate.this);
                myDB.deleteOneRow2(id);
                finish();
                }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
