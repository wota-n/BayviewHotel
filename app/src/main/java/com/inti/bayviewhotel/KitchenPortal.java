package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;


public class KitchenPortal extends AppCompatActivity {
    Button button1, button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_portal);

        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KitchenAddIngredient();
            }
        });

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                KitchenIngredientList();
            }
        });

    }
    public void KitchenAddIngredient()
    {
        Intent intent = new Intent(this, KitchenAddItem.class);
        startActivity(intent);
    }

    public void KitchenIngredientList()
    {
        Intent intent = new Intent(this, KitchenItemList.class);
        startActivity(intent);
    }

}

