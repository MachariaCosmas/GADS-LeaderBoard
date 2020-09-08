package com.example.gads2020practice.LearningHours;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gads2020practice.R;

import java.util.List;

public class Frag1Adapter extends RecyclerView.Adapter<Frag1Adapter.ViewHolder> {
    List<WatchHours> hourLists;
    Frag1 context;

    public Frag1Adapter(List<WatchHours> hourLists, Frag1 context) {
        this.hourLists = hourLists;
        this.context = context;
    }


    @NonNull
    @Override
    public Frag1Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_frag1,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Frag1Adapter.ViewHolder holder, int position) {
        WatchHours hourList=hourLists.get(position);
        holder.tvname.setText(hourList.getName());
        //holder.tvhours.setText(hourList.getHours());
        holder.tvhours.setText(String.valueOf(hourList.getHours()+" learning hours, "+hourList.getCountry()+"."));
       // holder.tvcountry.setText(hourList.getCountry());

    }

    @Override
    public int getItemCount() {
        return hourLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvname,tvhours,tvcountry;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvname=(TextView)itemView.findViewById(R.id.listName);
            tvhours=(TextView)itemView.findViewById(R.id.listHours);

            //tvcountry=(TextView)itemView.findViewById(R.id.listCountry);
        }
    }
}
