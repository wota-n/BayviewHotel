package com.inti.bayviewhotel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList item_id, item_name, item_status, item_quantity;

    CustomAdapter(Activity activity, Context context, ArrayList item_id, ArrayList item_name, ArrayList item_status,
                  ArrayList item_quantity){
        this.activity = activity;
        this.context = context;
        this.item_id = item_id;
        this.item_name = item_name;
        this.item_status = item_status;
        this.item_quantity = item_quantity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.item_id_txt.setText(String.valueOf(item_id.get(position)));
        holder.item_name_txt.setText(String.valueOf(item_name.get(position)));
        holder.item_status_txt.setText(String.valueOf(item_status.get(position)));
        holder.item_quantity_txt.setText(String.valueOf(item_quantity.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, KitchenUpdateItem.class);
                intent.putExtra("id", String.valueOf(item_id.get(position)));
                intent.putExtra("title", String.valueOf(item_name.get(position)));
                intent.putExtra("author", String.valueOf(item_status.get(position)));
                intent.putExtra("pages", String.valueOf(item_quantity.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return item_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_id_txt, item_name_txt, item_status_txt, item_quantity_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_id_txt = itemView.findViewById(R.id.item_id_txt);
            item_name_txt = itemView.findViewById(R.id.item_name_txt);
            item_status_txt = itemView.findViewById(R.id.item_status_txt);
            item_quantity_txt = itemView.findViewById(R.id.item_quantity_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
