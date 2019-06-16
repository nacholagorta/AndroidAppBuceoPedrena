package com.example.pccom.androidappbuceopedrena.FragmentNavigationBar;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pccom.androidappbuceopedrena.HttpRequest;
import com.example.pccom.androidappbuceopedrena.R;
import com.example.pccom.androidappbuceopedrena.Salida;
import com.example.pccom.androidappbuceopedrena.SalidasAdapter;
import com.example.pccom.androidappbuceopedrena.User;
import com.example.pccom.androidappbuceopedrena.UserAdapter;
import com.example.pccom.androidappbuceopedrena.UserSingletonID;

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
public class PerfilFragment extends Fragment {

    private static ProgressDialog mProgressDialog;

    private String jsonURL = "https://apibuceo.azurewebsites.net/Aplicacion/get_user_logged_info.php";
    private final int jsoncode = 1;
    ArrayList<User> userArrayList;
    private UserAdapter userAdapter;
    private ListView listView;

/*Fragmento con un List view para recoger los campos del usuario actual*/

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        listView = view.findViewById(R.id.userListView);
        fetchJSON();
        // Inflate the layout for this fragment
        return view;
    }

    /*Se recogen los datos en formato jSON con la url dada al principio de la clase*/
    @SuppressLint("StaticFieldLeak")
    private void fetchJSON(){



        new AsyncTask<Void, Void, String>(){
            protected String doInBackground(Void[] params) {
                String response="";
                HashMap<String, String> map=new HashMap<>();
                try {
                    HttpRequest req = new HttpRequest(jsonURL);
                    BufferedReader bufferedReader = null;
                    UserSingletonID classSingleton1 = UserSingletonID.getInstance();
                    Log.d("qwerty",classSingleton1.getInfo().substring(13));
                    String userID =classSingleton1.getInfo().substring(13);

                    URL url = new URL(jsonURL);
                    HttpURLConnection httpURLConnection =(HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);

                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String post_data = URLEncoder.encode("UserID", "UTF-8")+"="+ URLEncoder.encode(userID, "UTF-8");
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
                    response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();

                    return sb.toString().trim();
                    //response = req.prepare(HttpRequest.Method.POST).withData(map).sendAndReadString();
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

/*Si el JSON recibido es correcto, iniciará el volcado de datos desde la bbdd a la lista de este Fragment*/
    public void onTaskCompleted(String response, int serviceCode) {
        Log.d("responsejson", response.toString());
        switch (serviceCode) {
            case jsoncode:
                if (isSuccess(response)) {
                    removeSimpleProgressDialog();  //will remove progress dialog
                    userArrayList = getInfo(response);
                    userAdapter = new UserAdapter(getContext(),userArrayList);
                    listView.setAdapter(userAdapter);

                }else {
                    Toast.makeText(getActivity(), getErrorCode(response), Toast.LENGTH_SHORT).show();
                }
        }
    }

    /*Se asignan los valores recogidos del JSON a un objeto de tipo User*/

    public ArrayList<User> getInfo(String response) {
        ArrayList<User> userArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getJSONArray("usuario")!= null) {

                JSONArray dataArray = jsonObject.getJSONArray("usuario");

                for (int i = 0; i < dataArray.length(); i++) {

                    User usuario = new User();
                    JSONObject dataobj = dataArray.getJSONObject(i);
                    usuario.setNombre_user("Nombre: "+dataobj.getString("Nombre"));
                    usuario.setApellido_user("Apellido: "+dataobj.getString("Apellidos"));
                    usuario.setTelefono_user("Teléfono: "+dataobj.getString("Telefono"));
                    usuario.setDni_user("DNI: "+dataobj.getString("DNI"));
                    usuario.setTitulacion_user("Titulacion: "+dataobj.getString("Titulacion"));
                    userArrayList.add(usuario);

                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("1234",""+ userArrayList);
        return userArrayList;
    }

    /*Comprobación de que el JSON no llegue vacio*/
    public boolean isSuccess(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            if (jsonObject.getJSONArray("usuario")!= null) {
                return true;
            } else {

                return false;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }


    /*Cualquier error que provoque aparecerá en forma de Toast*/
    public String getErrorCode(String response) {

        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString("message");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "No data";
    }

    /*Métodos para mostrar un progreso de carga de los JSON*/
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


