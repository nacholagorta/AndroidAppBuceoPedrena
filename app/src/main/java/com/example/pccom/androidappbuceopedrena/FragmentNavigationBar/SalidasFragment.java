package com.example.pccom.androidappbuceopedrena.FragmentNavigationBar;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pccom.androidappbuceopedrena.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalidasFragment extends Fragment{
    Button selectDate;
    TextView date;
    DatePickerDialog datePickerDialog;

    public SalidasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_salidas, container, false);

        view.findViewById(R.id.btnDate).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                obtenerFecha();
            }
        });
        date = view.findViewById(R.id.tvSelectedDate);

        String [] listItems = {"Keko                    Isla Mouro                  9:00", "Alberto                La Catedral                11:30" , "Cristina               Plaza de Toros           16:30"};

        ListView listView = view.findViewById(R.id.list_salidas);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_expandable_list_item_1,
                listItems
        );

        listView.setAdapter(listViewAdapter);


        // Inflate the layout for this fragment
        return view;

    }



    public void obtenerFecha(){

        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                int mesActual = month + 1;
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);

                date.setText(diaFormateado + "/" + mesFormateado +"/"+ year);

            }
        },yy, mm, dd);

        datePickerDialog.show();

    }

}

