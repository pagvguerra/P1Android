package androidjavaucam.p1android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Pablo on 16/10/2016.
 */
public class TelaCadastroActivity extends AppCompatActivity {

    private EditText nome;
    private EditText matricula;
    private EditText conta;
    private EditText senha;
    private Spinner curso;
    private EditText email;
    private String emailSessao;
    private int idSessao;

    private Button botaoOK;
    private Button botaoLimpar;
    private Button botaoVoltar;

    private TextView mensagemRetorno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        SharedPreferences pref = getSharedPreferences("CONFIGURACOES", MODE_PRIVATE);
        emailSessao   = pref.getString("email", emailSessao);
        Usuario usuario = new UsuarioDAO().getUsuario(emailSessao, context);

        ((EditText) findViewById(R.id.editText5)).setText(usuario.getNome());
        ((EditText) findViewById(R.id.editText3)).setText(usuario.getEmail());
        ((EditText) findViewById(R.id.editText20)).setText(usuario.getSenha());
        ((EditText) findViewById(R.id.editText4)).setText(usuario.getMatricula());
        ((EditText) findViewById(R.id.editText6)).setText(usuario.getConta());

        curso = (Spinner) findViewById(R.id.spinner);
        carregaListaDeCursos(context);

        //Resgatando os botoes da página
        botaoOK = (Button) findViewById(R.id.button);
        botaoVoltar = (Button) findViewById(R.id.button3);

        botaoOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Resgatando os campos de texto da página
                nome = (EditText) findViewById(R.id.editText5);
                email = (EditText) findViewById(R.id.editText3);
                senha = (EditText) findViewById(R.id.editText20);
                matricula = (EditText) findViewById(R.id.editText4);
                conta = (EditText) findViewById(R.id.editText6);
                curso = (Spinner)  findViewById(R.id.spinner);
                mensagemRetorno = (TextView) findViewById(R.id.textView3);

                if (cadastraUsuario(context)) {
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

    private boolean cadastraUsuario(Context context) {

        SharedPreferences pref = getSharedPreferences("CONFIGURACOES", MODE_PRIVATE);
        emailSessao   = pref.getString("email", emailSessao);

        String nomeString = nome.getText().toString();
        String emailUsuarioString = email.getText().toString();
        String matriculaString = matricula.getText().toString();
        String senhaString = senha.getText().toString();
        String contaString = conta.getText().toString();
        int cursoInt = curso.getSelectedItemPosition();

        boolean isUpdate = false;

        if(nomeString == null || nomeString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Nome");
            return false;
        }

        if (emailUsuarioString == null || emailUsuarioString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Email do Usuário");
            return false;
        }

        if (!new EmailValidator().validate(emailUsuarioString)) {
            mensagemRetorno.setText("O Email digitado é inválido");
            return false;
        }

        if(emailSessao.equalsIgnoreCase(emailUsuarioString)) {
            isUpdate = true;
        }

        if(senhaString == null || senhaString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Senha");
            return false;
        }

        if(matriculaString == null || matriculaString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Matrícula");
            return false;
        }

        if(contaString == null || contaString.equals("")) {
            mensagemRetorno.setText("Preencha o campo Conta");
            return false;
        }

        Curso curso = new Curso();
        curso.setId(cursoInt);

        Usuario usuario = new Usuario();
        usuario.setNome(nomeString);
        usuario.setEmail(emailUsuarioString);
        usuario.setMatricula(matriculaString);
        usuario.setSenha(senhaString);
        usuario.setConta(contaString);
        usuario.setCurso(curso);

        boolean funcionou = false;

        if(isUpdate) {
            idSessao = pref.getInt("idUsuario", idSessao);
            usuario.setId(idSessao);
            funcionou = new UsuarioDAO().atualizaUsuario(usuario, context);
        } else {
            funcionou = new UsuarioDAO().cadastraUsuario(usuario, context);
        }


        if(!funcionou) {
            if(isUpdate) {
                mensagemRetorno.setText("Erro ao atualizar os dados de usuário");
            } else {
                mensagemRetorno.setText("Erro ao cadastrar os dados de usuário");
            }
            return false;
        }

        return true;
    }


    private void carregaListaDeCursos(Context context) {
        List<String> listaCursos = new CursoDAO().listaCursos(context);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaCursos);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curso.setAdapter(dataAdapter);
    }

}