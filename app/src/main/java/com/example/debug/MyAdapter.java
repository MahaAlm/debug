package com.example.debug;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyviewHolder> {
    private ArrayList name, rate, id;
    private Context context;
    ArrayList <toolModel> tool;
    private SelectListener listener;

    public MyAdapter(Context context, ArrayList name, ArrayList rate, ArrayList id ,SelectListener listener) {
        this.context = context;
        this.listener=listener;
        this.tool=tool;
        this.name = name;
        this.rate = rate;
        this.id = id;

    }

    public MyAdapter(Context context, ArrayList tool) {
        this.context = context;
        this.tool = tool;

    }



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.display,parent,false);
        return new MyviewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.model.setText(String.valueOf(name.get(position)));
        holder.rent.setText(String.valueOf(rate.get(position)));
        holder.id.setText("id: "+String.valueOf(id.get(position)));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(Integer.parseInt(String.valueOf(id.get(position))));


            }
        });







    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class MyviewHolder extends RecyclerView.ViewHolder {
        TextView model , rent, id;
        CardView cardView;


        public MyviewHolder(@NonNull View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.item_name);
            rent = itemView.findViewById(R.id.textPrice);
            id=itemView.findViewById(R.id.it_id);
            cardView=itemView.findViewById(R.id.main_container);




        }



    }
}






