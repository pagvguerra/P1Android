package androidjavaucam.p1android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Pablo on 12/10/2016.
 */
public class TelaTarefaActivity extends AppCompatActivity {

    private Button botaoProgresso;
    private Button botaoCadastro;
    private Button botaoSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        //Resgatando os botoes da página
        botaoProgresso = (Button) findViewById(R.id.button3);
        botaoCadastro = (Button) findViewById(R.id.button4);
        botaoSair = (Button) findViewById(R.id.button5);

        //Fazendo a acao do clique no botão Limpar
        botaoSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaTarefaActivity.this, PrincipalActivity.class));
            }
        });

        botaoProgresso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaTarefaActivity.this, TelaProgressoActivity.class));
            }
        });

        botaoCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaTarefaActivity.this, TelaCadastroActivity.class));
            }
        });
    }

}
