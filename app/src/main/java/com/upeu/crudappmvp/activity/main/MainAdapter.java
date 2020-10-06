package com.upeu.crudappmvp.activity.main;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.upeu.crudappmvp.R;
import com.upeu.crudappmvp.model.Alumno;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecylerViewAdapater> {
    private Context context;
    private List<Alumno> alumnos = new ArrayList<>();
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<Alumno> alumnos, ItemClickListener itemClickListener) {
        this.context = context;
        this.alumnos = alumnos;
        this.itemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecylerViewAdapater onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_student,
                parent, false);
        return new RecylerViewAdapater(view, itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapater holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.tv_name.setText(alumno.getName());
        holder.tv_email.setText(alumno.getEmail());
        holder.tv_date.setText(alumno.getDate());
        holder.card_item.setCardBackgroundColor(Color.rgb(255, 160, 122));
    }

    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    class RecylerViewAdapater extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tv_name, tv_email, tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;

        RecylerViewAdapater(@NonNull View itemView, ItemClickListener itemClickListener) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
            tv_email = itemView.findViewById(R.id.email);
            tv_date = itemView.findViewById(R.id.date);
            card_item = itemView.findViewById(R.id.card_item);

            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
