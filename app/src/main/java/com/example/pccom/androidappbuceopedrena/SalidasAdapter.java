package com.example.pccom.androidappbuceopedrena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class SalidasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Salida> salidaArrayList;

    public SalidasAdapter(Context context, ArrayList<Salida> salidaArrayList) {

        this.context = context;
        this.salidaArrayList = salidaArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return salidaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return salidaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.display_salidas_row, null, true);

            holder.tvinstructor = (TextView) convertView.findViewById(R.id.tv_instructor);
            holder.tvlugar = (TextView) convertView.findViewById(R.id.tv_lugar);
            holder.tvhora = (TextView) convertView.findViewById(R.id.tv_hora);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvinstructor.setText(salidaArrayList.get(position).getNombre_instructor());
        holder.tvlugar.setText(salidaArrayList.get(position).getLugar());
        holder.tvhora.setText(salidaArrayList.get(position).getHora());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvinstructor, tvlugar, tvhora;

    }
}
