package com.inti.bayviewhotel;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.ImageView;


public class KitchenPortal extends AppCompatActivity implements View.OnClickListener{

    ImageView additem, itemlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen_portal);

        additem = (ImageView) findViewById(R.id.additem);
        additem.setOnClickListener(this);

        itemlist = (ImageView) findViewById(R.id.itemlist);
        itemlist.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.additem:
                startActivity(new Intent(this, KitchenAddItem.class));
                break;

            case R.id.itemlist:
                startActivity(new Intent(this, KitchenItemList.class));

                break;

        }
    }

}

