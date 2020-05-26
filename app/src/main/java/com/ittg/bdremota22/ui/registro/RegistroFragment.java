package com.ittg.bdremota22.ui.registro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ittg.bdremota22.R;

import org.json.JSONObject;

public class RegistroFragment extends Fragment implements Response.Listener<JSONObject>, Response.ErrorListener{

    private RegistroViewModel galleryViewModel;
    EditText tnombre, tapellidos, tedad;
    Button btnRegistrar;
    ProgressDialog progreso;

    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(RegistroViewModel.class);
        View root = inflater.inflate(R.layout.fragment_registro, container, false);
        tnombre=(EditText)root.findViewById(R.id.etNombre);
        tapellidos=(EditText)root.findViewById(R.id.etApellidos);
        tedad=(EditText)root.findViewById(R.id.etEdad);
        btnRegistrar=(Button)root.findViewById(R.id.btnRegistrar);

        request= Volley.newRequestQueue(getContext());
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargarWebService();
            }
        });

        return root;
    }
    public void cargarWebService(){
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Cargando");
        progreso.show();

        String url="https://android2019ejemplo4.000webhostapp.com/registro.php?nombre="+tnombre.getText().toString()
                +"&apellidos="+tapellidos.getText().toString()+"&edad="+tedad.getText().toString();
        url=url.replace(" ","%20");
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url, null, this,this);
        request.add(jsonObjectRequest);


    }
    @Override
    public void onErrorResponse(VolleyError error) {
        progreso.hide();
        Toast.makeText(getContext(), "No se puede guardar", Toast.LENGTH_SHORT).show();
        Log.i("Error", error.toString());

    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(getContext(), "Registro guardado", Toast.LENGTH_SHORT).show();
        progreso.hide();
        tnombre.setText("");
        tapellidos.setText("");
        tedad.setText("");

    }
}