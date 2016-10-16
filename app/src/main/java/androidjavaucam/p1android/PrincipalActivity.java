package androidjavaucam.p1android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PrincipalActivity extends AppCompatActivity {

    private Button botaoAcessar;
    private Button botaoLimpar;
    private EditText emailUsuario;
    private EditText passwordUsuario;
    private TextView mensagemRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //Resgatando os campos de texto da página
        emailUsuario = (EditText) findViewById(R.id.editText2);
        passwordUsuario = (EditText) findViewById(R.id.editText);
        mensagemRetorno = (TextView) findViewById(R.id.textView3);

        //Resgatando os botoes da página
        botaoLimpar = (Button) findViewById(R.id.button2);
        botaoAcessar = (Button) findViewById(R.id.button);

        //Fazendo a acao do clique no botão Limpar
        botaoLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limparCamposAction();
            }
        });

        botaoAcessar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validaCampos()) {
                    startActivity(new Intent(PrincipalActivity.this, TelaTarefaActivity.class));
                }
            }
        });

    }

    private void limparCamposAction() {
        emailUsuario.setText(null);
        passwordUsuario.setText(null);
        mensagemRetorno.setText(null);
    }

    private boolean validaCampos() {
        String emailUsuarioString = emailUsuario.getText().toString();
        String passwordUsuarioString = passwordUsuario.getText().toString();

        if (emailUsuarioString == null || emailUsuarioString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Email do Usuário");
            return false;
        }

        if(!new EmailValidator().validate(emailUsuarioString)){
            mensagemRetorno.setText("O Email digitado é inválido");
            return false;
        }

        if (passwordUsuarioString == null || passwordUsuarioString.equals("")) {
            mensagemRetorno.setText("Preencha o campo de Password");
            return false;
        }

        return true;
    }

}
