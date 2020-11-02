package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KitchenAddItem extends AppCompatActivity {

    EditText item_name, item_status, item_quantity;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_add_item);

        item_name = findViewById(R.id.item_name);
        item_status = findViewById(R.id.item_status);
        item_quantity = findViewById(R.id.item_quantity);
        add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(KitchenAddItem.this);
                myDB.addBook(item_name.getText().toString().trim(),
                        item_status.getText().toString().trim(),
                        Integer.valueOf(item_quantity.getText().toString().trim()));
            }
        });
    }
}
