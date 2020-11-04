package com.inti.bayviewhotel;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HousekeepingRoomList extends AppCompatActivity {

    private Button button;
    RecyclerView recyclerView;
    FloatingActionButton add_button;
    MyDatabaseHelper2 myDB;
    ArrayList<String> room_id, _type, _status, _staff;
    HousekeepingAdapter housekeepingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_housekeeping_roomlist);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HousekeepingRoomList.this, HousekeepingAd.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper2(HousekeepingRoomList.this);
        room_id = new ArrayList<>();
        _type = new ArrayList<>();
        _status = new ArrayList<>();
        _staff = new ArrayList<>();
        storeDataInArray();
        housekeepingAdapter = new HousekeepingAdapter( HousekeepingRoomList.this, this, room_id, _type, _status, _staff);
        recyclerView.setAdapter(housekeepingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HousekeepingRoomList.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, requestCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArray(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "No", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                room_id.add(cursor.getString(0));
                _type.add(cursor.getString(1));
                _status.add(cursor.getString(2));
                _staff.add(cursor.getString(3));
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem room) {
        if(room.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(room);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper2 myDB2 = new MyDatabaseHelper2(HousekeepingRoomList.this);
                myDB2.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(HousekeepingRoomList.this, HousekeepingRoomList.class);
                startActivity(intent);
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