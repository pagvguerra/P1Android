package androidjavaucam.p1android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 12/10/2016.
 */
public class TelaTarefaActivity extends AppCompatActivity {

    private Button botaoProgresso;
    private Button botaoCadastro;
    private Button botaoSair;

    private TextView blocoAula;
    private CheckBox exercicio;
    private CheckBox laboratorio;
    private ProgressBar progressoBar;
    private Spinner curso;
    private Spinner aula;
    private int idSessao;
    private int fkCurso;
    private int fkAula;

    private boolean exercicioPraGravarNoBanco = false;
    private boolean laboratorioPraGravarNoBanco = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        SharedPreferences pref = getSharedPreferences("CONFIGURACOES", MODE_PRIVATE);

        curso = (Spinner) findViewById(R.id.spinner);
        aula  = (Spinner) findViewById(R.id.spinner1);

        blocoAula = (TextView) findViewById(R.id.textView3);
        exercicio = (CheckBox) findViewById(R.id.checkBox2);
        laboratorio = (CheckBox) findViewById(R.id.checkBox);
        progressoBar = (ProgressBar) findViewById(R.id.progressBar);

        //Resgatando os botoes da página
        botaoProgresso = (Button) findViewById(R.id.button3);
        botaoCadastro = (Button) findViewById(R.id.button4);
        botaoSair = (Button) findViewById(R.id.button5);

        carregaListaDeCursos(context);

        idSessao = pref.getInt("idUsuario", idSessao);


        curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fkCurso = position+1;
                carregaListaDeAula(context, position+1);

                aula.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        fkAula = Integer.parseInt(String.valueOf(parent.getItemIdAtPosition(position)));
                        blocoAula.setText((String) parent.getItemAtPosition(position));
                     }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {}
                });
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        exercicio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(exercicio.isChecked()) {
                    exercicioPraGravarNoBanco = true;
                } else {
                    exercicioPraGravarNoBanco = false;
                }
                setaBarraDeProgressoDasTarefas();
            }
        });

        laboratorio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(laboratorio.isChecked()) {
                    laboratorioPraGravarNoBanco = true;
                } else {
                    laboratorioPraGravarNoBanco = false;
                }
                setaBarraDeProgressoDasTarefas();
            }
        });

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

                Tarefa tarefa = new Tarefa();
                tarefa.setFkAluno(idSessao);
                tarefa.setFkCurso(fkCurso);
                tarefa.setFkAula(fkAula);
                tarefa.setExercicioFeito(exercicioPraGravarNoBanco);
                tarefa.setLaboratorioEnviado(laboratorioPraGravarNoBanco);
                new TarefaDAO().cadastrarTarefa(tarefa, context);

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

    private void setaBarraDeProgressoDasTarefas(){
        if(exercicioPraGravarNoBanco && laboratorioPraGravarNoBanco){
            progressoBar.setProgress(5000);
        } else if( (exercicioPraGravarNoBanco && !laboratorioPraGravarNoBanco) || (!exercicioPraGravarNoBanco && laboratorioPraGravarNoBanco)){
            progressoBar.setProgress(2500);
        }else if (!exercicioPraGravarNoBanco && !laboratorioPraGravarNoBanco) {
            progressoBar.setProgress(0);
        }
    }

    private void carregaListaDeCursos(Context context) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new CursoDAO().listaCursos(context));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        curso.setAdapter(dataAdapter);
    }

    private void carregaListaDeAula(Context context, int posicao) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new AulaDAO().listaAulas(context, posicao));
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        aula.setAdapter(dataAdapter);
    }

}