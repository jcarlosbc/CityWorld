package com.hispano_mx.cityworld.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hispano_mx.cityworld.R;
import com.hispano_mx.cityworld.models.Ciudad;
import com.squareup.picasso.Picasso;

import java.util.List;

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


        public MyViewHolder(View itemView) {
            super(itemView);

            img_city = (ImageView) itemView.findViewById(R.id.img_city);
            city_name = (TextView) itemView.findViewById(R.id.txt_city_name);
            city_rating = (TextView) itemView.findViewById(R.id.txt_rating);
            city_desc = (TextView) itemView.findViewById(R.id.txt_desc);
        }

        public void bind(Ciudad ciudad, OnJCItemClickListener itemClickListener) {
            Log.i("MyViewHolder","bind() img_url->"+ciudad.getUrl_img());

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
