package com.example.pccom.androidappbuceopedrena.FragmentNavigationBar;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.pccom.androidappbuceopedrena.HttpRequest;
import com.example.pccom.androidappbuceopedrena.R;
import com.example.pccom.androidappbuceopedrena.Salida;
import com.example.pccom.androidappbuceopedrena.SalidasAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 */
public class SalidasFragment extends Fragment{

    TextView date;
    DatePickerDialog datePickerDialog;
    private static ProgressDialog mProgressDialog;
    private String jsonURL = "http://192.168.1.117/android_buceopedrena/get_salidas.php";
    private final int jsoncode = 1;
    private ListView listView;
    ArrayList<Salida> salidaArrayList;
    private SalidasAdapter salidasAdapter;

    /*Fragmento con un List view para recoger los campos de la salida segun la fecha seleccionada*/


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

        listView = view.findViewById(R.id.list_salidas);

        /* Si el Text View que almacena la fecha cambia su contenido, se llamará a la función fetchJSON()
        pasandole or parámetro la fecha seleccionada*/

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d("cambiofecha",date.getText().toString());
                fetchJSON(date.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        // Inflate the layout for this fragment
        return view;

    }

    /*Se recogen los datos en formato jSON con la url dada al principio de la clase*/
    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(final String fecha){

        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {

                    BufferedReader bufferedReader = null;

                    URL url = new URL(jsonURL);
                    HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    /*Se envia por método POST la información de la fecha seleccionada para que el php lo recoja*/
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("Fecha", "UTF-8")+"="+ URLEncoder.encode(fecha, "UTF-8");
                    bufferedWriter.write(post_data);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();


                    StringBuilder sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    bufferedReader.close();
                    httpURLConnection.disconnect();
                    /*Otra manera de recoger datos JSON mapeandolos*/
                    //HttpRequest req = new HttpRequest(jsonURL);
                    //response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
                    return sb.toString().trim();

                } catch (Exception e) {
                    response=e.getMessage();
                }
                return response;
            }
            protected void onPostExecute(String result) {
                //do something with response
                Log.d("newwwss",result);
                onTaskCompleted(result,jsoncode);
            }
        }.execute();
    }

/*Método para obtener la fecha con DateFormat y mostrar el Date Picker Dialog*/
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

    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        Log.d("responsejson", serviceCode +"");
        switch (serviceCode) {
            case jsoncode:
                Log.d("responsejson", "hola");
                if (isSuccess(response)) {
                    Log.d("responsejson", "hola");
                    removeSimpleProgressDialog();  //will remove progress dialog
                    salidaArrayList = getInfo(response);
                    salidasAdapter = new SalidasAdapter(getContext(),salidaArrayList);
                    listView.setAdapter(salidasAdapter);

                }else {
                    Log.d("responsejson", "hola");
                    Toast.makeText(getActivity(), getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }


    public ArrayList<Salida> getInfo(String response) {
        ArrayList<Salida> salidasArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getJSONArray("salidas")!= null) {

                JSONArray dataArray = jsonObject.getJSONArray("salidas");

                for (int i = 0; i < dataArray.length(); i++) {

                    Salida salida = new Salida();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    salida.setNombre_instructor(dataobj.getString("Nombre"));
                    salida.setLugar(dataobj.getString("Lugar"));
                    salida.setHora(dataobj.getString("Hora"));
                    salidasArrayList.add(salida);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("1234",""+ salidasArrayList);
        return salidasArrayList;
    }


    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getJSONArray("salidas")!= null) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

