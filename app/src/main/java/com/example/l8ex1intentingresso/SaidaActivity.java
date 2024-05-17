package com.example.l8ex1intentingresso;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.l8ex1intentingresso.model.Ingresso;
import com.example.l8ex1intentingresso.model.IngressoVIP;

public class SaidaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_saida);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tvDados = findViewById(R.id.tvDados);
        tvDados.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        TextView tvSaida = findViewById(R.id.tvSaida);
        tvSaida.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Button btnVoltar = findViewById(R.id.btnVoltar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        
        String id = bundle.getString("id");
        float valor = bundle.getFloat("valor");
        float valorFinal = bundle.getFloat("valorFinal");
        String tipo = bundle.getString("tipo");
        if (tipo.equals("ingresso")){
            Ingresso i = new Ingresso();
            i.setId(id);
            i.setValor(valor);
            tvSaida.setText(i.toString()+getString(R.string.valorfinal)+valorFinal);
        }
        else{
            IngressoVIP i = new IngressoVIP();
            i.setId(id);
            i.setValor(valor);
            i.setFuncao(bundle.getString("funcao"));
            tvSaida.setText(i.toString()+getString(R.string.valorfinal)+valorFinal);
        }
        
        btnVoltar.setOnClickListener(op -> voltar());
    }

    private void voltar() {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
        this.finish();
    }
}