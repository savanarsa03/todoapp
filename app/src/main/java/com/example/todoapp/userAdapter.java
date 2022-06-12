package com.example.todoapp;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class userAdapter extends RecyclerView.Adapter<userAdapter.MyViewHolder> {
    private Context context;
    private List<user>list;
    private Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public userAdapter(Context context, List<user> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public userAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_user, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
      holder.name.setText(List.get(position).getName());
      holder.email.setText(List.get(position).getEmail());
    }
    @Override
    public int getItemCount(){
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email;

        public MyViewHolder( View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.email.setText(list.get(position).getEmail());
    }
    public int getItemCount(){
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, email;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}