package com.example.gads2020practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Frag2Adapter extends RecyclerView.Adapter<Frag2Adapter.ViewHolder> {
    List<SkillIQ> IQLists;
    Frag2 context;

    public Frag2Adapter(List<SkillIQ> IQLists, Frag2 context) {
        this.IQLists = IQLists;
        this.context = context;
    }


    @NonNull
    @Override
    public Frag2Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_single_frag2,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Frag2Adapter.ViewHolder holder, int position) {
        SkillIQ IQList=IQLists.get(position);
        holder.tvname.setText(IQList.getName());
        //holder.tvhours.setText(hourList.getHours());
        holder.tvhours.setText(String.valueOf(IQList.getScore()+" skill IQ score, "+IQList.getCountry()+"."));
       // holder.tvcountry.setText(hourList.getCountry());
    }

    @Override
    public int getItemCount() {
        return IQLists.size();
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
