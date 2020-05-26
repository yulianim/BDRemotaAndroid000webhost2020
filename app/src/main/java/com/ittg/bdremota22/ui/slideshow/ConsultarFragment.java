package com.ittg.bdremota22.ui.slideshow;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.ittg.bdremota22.R;
import com.ittg.bdremota22.adapter.UsuariosAdapter;
import com.ittg.bdremota22.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ConsultarFragment extends Fragment implements Response.ErrorListener, Response.Listener<JSONObject>{
    RecyclerView recyclerUsuarios;
    ArrayList<Usuario> listaUsuarios;
    ProgressDialog progreso;
    RequestQueue request;
    JsonObjectRequest jsonObjectRequest;

    private ConsultarViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(ConsultarViewModel.class);
        View root = inflater.inflate(R.layout.fragment_consultar, container, false);
        listaUsuarios=new ArrayList<>();
        recyclerUsuarios=(RecyclerView)root.findViewById(R.id.rvUsuarios);
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerUsuarios.setHasFixedSize(true);
        request= Volley.newRequestQueue(getContext());
        cargarWebService();
        return root;
    }

    private void cargarWebService() {
        progreso=new ProgressDialog(getContext());
        progreso.setMessage("Conectando");
        progreso.show();
        String url="https://android2019ejemplo4.000webhostapp.com/consultar.php";
        jsonObjectRequest=new JsonObjectRequest(Request.Method.GET,url, null, this, this);
        request.add(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getContext(),"No se pudo conectar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("Error",error.toString());
        progreso.hide();


    }

    @Override
    public void onResponse(JSONObject response) {
        Usuario usuario=null;
        JSONArray json=response.optJSONArray("usuario");
        try {
            for (int i = 0; i < json.length(); i++) {
                usuario = new Usuario();
                JSONObject jsonObject = null;
                jsonObject = json.getJSONObject(i);
                usuario.setNombre(jsonObject.optString("nombre"));
                usuario.setApellidos(jsonObject.optString("apellidos"));
                usuario.setEdad(jsonObject.optString("edad"));
                listaUsuarios.add(usuario);

            }
            progreso.hide();
            UsuariosAdapter adapter=new UsuariosAdapter(listaUsuarios);
            recyclerUsuarios.setAdapter(adapter);
        }
        catch (JSONException e){
            e.printStackTrace();
            Toast.makeText(getContext(),"No se pudo establecer conexion con el servidor"+""+response,Toast.LENGTH_LONG).show();
            progreso.hide();

        }

    }
}