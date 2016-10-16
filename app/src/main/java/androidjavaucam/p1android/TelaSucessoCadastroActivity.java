package androidjavaucam.p1android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pablo on 16/10/2016.
 */
public class TelaSucessoCadastroActivity  extends AppCompatActivity {

    private Button botaoVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucesso_cadastro);

        //Resgatando os botoes da página
        botaoVoltar = (Button) findViewById(R.id.button2);

        //Fazendo a acao do clique no botão Voltar
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaSucessoCadastroActivity.this, TelaTarefaActivity.class));
            }
        });

    }

}
