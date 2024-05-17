package com.example.l8ex1intentingresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.l8ex1intentingresso.model.Ingresso;
import com.example.l8ex1intentingresso.model.IngressoVIP;

public class MainActivity extends AppCompatActivity {

    private CheckBox cbVIP;
    private EditText etId;
    private EditText etValor;
    private EditText etFuncao;
    private EditText etTaxa;
    private Button btnComprar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        cbVIP = findViewById(R.id.cbVIP);
        etId = findViewById(R.id.etId);
        etValor = findViewById(R.id.etValor);
        etFuncao = findViewById(R.id.etFuncao);
        etTaxa = findViewById(R.id.etTaxa);
        btnComprar = findViewById(R.id.btnComprar);

        atualizaActivity();

        cbVIP.setOnCheckedChangeListener((buttonView, isChecked) -> atualizaActivity());
        btnComprar.setOnClickListener(op -> comprar());
    }

    private void comprar() {
        String id = etId.getText().toString();
        float valor = Float.parseFloat(etValor.getText().toString());
        float taxa = Float.parseFloat(etTaxa.getText().toString());
        float valorFinal = 0;
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putFloat("valor", valor);
        String tipo = "";
        if (cbVIP.isChecked()){
            tipo = "VIP";
            IngressoVIP i = new IngressoVIP();
            i.setValor(valor);
            String funcao = etFuncao.getText().toString();
            bundle.putString("funcao", funcao);
            valorFinal = i.valorFinal(taxa);
        }
        else {
            tipo = "ingresso";
            Ingresso i = new Ingresso();
            i.setValor(valor);
            valorFinal = i.valorFinal(taxa);
        }
        bundle.putString("tipo", tipo);
        bundle.putFloat("valorFinal", valorFinal);

        troca(bundle);
    }

    private void troca(Bundle bundle) {
        Intent i = new Intent(this, SaidaActivity.class);
        i.putExtras(bundle);
        this.startActivity(i);
        this.finish();
    }

    private void atualizaActivity() {
        if (!cbVIP.isChecked()){
            etFuncao.setVisibility(View.GONE);
        }
        else {
            etFuncao.setVisibility(View.VISIBLE);
        }
    }
}