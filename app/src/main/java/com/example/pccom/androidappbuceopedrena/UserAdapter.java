package com.example.pccom.androidappbuceopedrena;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<User> userArrayList;

    public UserAdapter(Context context, ArrayList<User> userArrayList) {

        this.context = context;
        this.userArrayList = userArrayList;
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
        return userArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return userArrayList.get(position);
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
            convertView = inflater.inflate(R.layout.display_user_row, null, true);

            holder.tvNombre = (TextView) convertView.findViewById(R.id.tvNombreUser);
            holder.tvApellido = (TextView) convertView.findViewById(R.id.tvApellidoUser);
            holder.tvTelefono = (TextView) convertView.findViewById(R.id.tvTelefonoUser);
            holder.tvDNI = (TextView) convertView.findViewById(R.id.tvDNIUser);
            holder.tvTitulacion = (TextView) convertView.findViewById(R.id.tvTitulacionUser);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvNombre.setText(userArrayList.get(position).getNombre_user());
        holder.tvApellido.setText(userArrayList.get(position).getApellido_user());
        holder.tvTelefono.setText(userArrayList.get(position).getTelefono_user());
        holder.tvDNI.setText(userArrayList.get(position).getDni_user());
        holder.tvTitulacion.setText(userArrayList.get(position).getTitulacion_user());

        return convertView;
    }

    private class ViewHolder {

        protected TextView tvNombre, tvApellido, tvTelefono, tvDNI, tvTitulacion;

    }
}
