package androidjavaucam.p1android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Pablo on 16/10/2016.
 */
public class TelaCadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText email;
    private EditText conta;

    private Button botaoOK;
    private Button botaoLimpar;
    private Button botaoVoltar;

    private TextView mensagemRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        //Resgatando os campos de texto da página
        nome = (EditText) findViewById(R.id.editText5);
        matricula = (EditText) findViewById(R.id.editText4);
        email = (EditText) findViewById(R.id.editText3);
        conta = (EditText) findViewById(R.id.editText6);
        mensagemRetorno = (TextView) findViewById(R.id.textView3);

        //Resgatando os botoes da página
        botaoOK = (Button) findViewById(R.id.button);
        botaoLimpar = (Button) findViewById(R.id.button2);
        botaoVoltar = (Button) findViewById(R.id.button3);

        //Fazendo a acao do clique no botão Limpar
        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCamposAction();
            }
        });

        botaoOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    startActivity(new Intent(TelaCadastroActivity.this, TelaSucessoCadastroActivity.class));
                }
            }
        });

        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TelaCadastroActivity.this, TelaTarefaActivity.class));
            }
        });

    }

    private void limparCamposAction() {
        nome.setText(null);
        matricula.setText(null);
        email.setText(null);
        conta.setText(null);
        mensagemRetorno.setText(null);
    }

    private boolean validaCampos() {

        String nomeString = nome.getText().toString();
        String matriculaString = matricula.getText().toString();
        String emailString = email.getText().toString();
        String contaString = conta.getText().toString();

        if(nomeString == null || nomeString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Nome");
            return false;
        }

        if(matriculaString == null || matriculaString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Matrícula");
            return false;
        }

        if (emailString == null || emailString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Email");
            return false;
        }

        if (!new EmailValidator().validate(emailString)) {
            mensagemRetorno.setText("O Email digitado é inválido");
            return false;
        }

        if(contaString == null || contaString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Conta");
            return false;
        }

        return true;
    }
}