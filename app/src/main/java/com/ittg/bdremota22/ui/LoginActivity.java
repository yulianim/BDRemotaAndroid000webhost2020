package com.ittg.bdremota22.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ittg.bdremota22.MainActivity;
import com.ittg.bdremota22.R;

import org.json.JSONArray;
import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {
    private EditText etUsuario, etPass;
    private Button btnIniciar;
    ProgressDialog progreso;
    RequestQueue request;
    JSONArray jsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsuario=(EditText)findViewById(R.id.etUsuario);
        etPass=(EditText)findViewById(R.id.etPass);
        btnIniciar=(Button)findViewById(R.id.btnIniciar);
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        request= Volley.newRequestQueue(this);
        String url="https://android2019ejemplo4.000webhostapp.com/login.php?user="+etUsuario.getText().toString();
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    jsonArray = new JSONArray(response);
                    String pass = jsonArray.getString(0);
                    if (pass.equals(etPass.getText().toString())) {
                        Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "Verifique su contraseña", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "El usuario no existe en la base de datos", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }); request.add(stringRequest);
    }
}
