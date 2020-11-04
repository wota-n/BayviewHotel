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

public class HousekeepingAdapter extends RecyclerView.Adapter<HousekeepingAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList room_id, _type, _status, _staff;
    int position;

    HousekeepingAdapter(Activity activity, Context context, ArrayList room_id, ArrayList _type, ArrayList _status, ArrayList _staff){
        this.activity = activity;
        this.context = context;
        this.room_id = room_id;
        this._type = _type;
        this._status = _status;
        this._staff = _staff;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        this.position = position;

        holder.room_id_txt.setText(String.valueOf(room_id.get(position)));
        holder.room_type_txt.setText(String.valueOf(_type.get(position)));
        holder.status_txt.setText(String.valueOf(_status.get(position)));
        holder.staff_txt.setText(String.valueOf(_staff.get(position)));
        holder.mainLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HousekeepingUpdate.class);
                intent.putExtra("id", String.valueOf(room_id.get(position)));
                intent.putExtra("type", String.valueOf(_type.get(position)));
                intent.putExtra("status", String.valueOf(_status.get(position)));
                intent.putExtra("staff", String.valueOf(_staff.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return room_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView room_id_txt, room_type_txt, status_txt, staff_txt;
        LinearLayout mainLayout2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            room_id_txt = itemView.findViewById(R.id.room_id_txt);
            room_type_txt = itemView.findViewById(R.id.room_type_txt);
            status_txt = itemView.findViewById(R.id.status_txt);
            staff_txt = itemView.findViewById(R.id.staff_txt);
            mainLayout2 = itemView.findViewById(R.id.mainLayout2);
        }
    }
}

