package com.hispano_mx.cityworld.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.models.Ciudad;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.realm.Realm;

/**
 * Created by jc on 11/03/17.
 */

public class CityMainAdapter extends RecyclerView.Adapter<CityMainAdapter.MyViewHolder>  {
    private List<Ciudad> list_ciudades;
    private int layout;
    private OnJCItemClickListener itemClickListener;
    private Context ctx;

    public CityMainAdapter(Context context,List <Ciudad> names, int layout, OnJCItemClickListener itemClickListener) {
        this.list_ciudades = names;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
        this.ctx = context;
        Log.i("MyAdapter>>","Constructor");
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        MyViewHolder vh = new MyViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(list_ciudades.get(position),itemClickListener);
        Log.i("MyAdapter>>","holder.bind");
    }

    @Override
    public int getItemCount() {
        return list_ciudades.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img_city;
        TextView city_name;
        TextView city_rating;
        TextView city_desc;
        Button delete_btn;


        public MyViewHolder(View itemView) {
            super(itemView);

            img_city = (ImageView) itemView.findViewById(R.id.img_city);
            city_name = (TextView) itemView.findViewById(R.id.txt_city_name);
            city_rating = (TextView) itemView.findViewById(R.id.txt_rating);
            city_desc = (TextView) itemView.findViewById(R.id.txt_desc);
            delete_btn = (Button) itemView.findViewById(R.id.delete_button);
            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final AlertDialog alertDialog;
                    AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                    builder.setMessage("Los datos no se podrán recuperar").setTitle("Borrar Ciudad");
                    builder.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Realm realm;
                            realm = Realm.getDefaultInstance();

                            realm.beginTransaction();
                            list_ciudades.get(getAdapterPosition()).deleteFromRealm();
                            realm.commitTransaction();

                            notifyDataSetChanged();
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    alertDialog = builder.create();
                    alertDialog.show();
                }
            });
        }

        public void bind(final Ciudad ciudad, final OnJCItemClickListener itemClickListener) {
            Log.i("MyViewHolder","bind() img_url->"+ciudad.getUrl_img());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onitemClickListener(ciudad,getAdapterPosition());
                }
            });

            Picasso.with(ctx)
                    .load(ciudad.getUrl_img())
                    .error(R.mipmap.ic_launcher)
                    .into(img_city);
            city_name.setText(ciudad.getNombre());
            city_desc.setText(ciudad.getDescripcion());
            city_rating.setText(Float.toString( ciudad.getEstrellas()));

        }
    }

    public interface OnJCItemClickListener {
        void onitemClickListener(Ciudad cd, int position);
    }
}
