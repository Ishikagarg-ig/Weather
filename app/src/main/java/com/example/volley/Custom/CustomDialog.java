package com.example.volley.Custom;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.sip.SipSession;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.volley.R;

public class CustomDialog extends Dialog {

    Context context;
    String[] cities;
    OnCitySelecListner listnerd;
    RecyclerView rec;

    public CustomDialog(@NonNull Context context, int LayoutResource, String[] c,OnCitySelecListner d){
        super(context);

        this.context=context;
        this.cities=c;

        this.setContentView(LayoutResource);
        this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        this.listnerd=d;

        rec=this.findViewById(R.id.rc);

        rec.setLayoutManager(new LinearLayoutManager(context));

        rec.setAdapter(new Adapter(context,cities,listnerd));

    }
}


class Adapter extends RecyclerView.Adapter<Adapter.Holder>{

    Context context;
    String[] cities;

    OnCitySelecListner onCitySelecListner;

    public Adapter(Context context, String[] cities,OnCitySelecListner listener) {
        this.context = context;
        this.cities = cities;
        this.onCitySelecListner= listener;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater=LayoutInflater.from(context);
        View v=layoutInflater.inflate(R.layout.card,parent,false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int i) {

        final int replace=i;

        holder.citytext.setText(cities[i]);
        holder.note.setText(""+cities[i].toCharArray()[0]);
        holder.citytext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCitySelecListner.OnSelected(replace);
            }
        });

    }

    @Override
    public int getItemCount() {
        return cities.length;
    }

    class Holder extends  RecyclerView.ViewHolder{

        TextView note,citytext;

        public Holder(@NonNull View itemView){
            super(itemView);

            note=itemView.findViewById(R.id.note);
            citytext=itemView.findViewById(R.id.citytext);


        }
    }

}