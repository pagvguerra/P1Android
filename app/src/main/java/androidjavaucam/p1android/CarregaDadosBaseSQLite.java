package androidjavaucam.p1android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Pablo on 10/12/2016.
 */
public class CarregaDadosBaseSQLite {

    private static final String LOGCAT      =   null;

    public void criaTabelas(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE aluno (_id integer PRIMARY KEY AUTOINCREMENT, nome text, matricula text, email text, senha text, contaGitHub text, fkCurso integer)");
            db.execSQL("CREATE TABLE curso (_id integer PRIMARY KEY AUTOINCREMENT, nome text)");
            db.execSQL("CREATE TABLE aula (_id integer PRIMARY KEY AUTOINCREMENT, nome text, fkCurso integer)");
            db.execSQL("CREATE TABLE tarefa (_id integer PRIMARY KEY AUTOINCREMENT, fkAluno integer, fkCurso integer, fkAula integer, exercicioFeito boolean, laboratorioEnviado boolean)");
            cargaInicial(db);
        }catch (Exception e) {}
    }

    public void cargaInicial(SQLiteDatabase db) {

        try {

            //tabela aluno
            ContentValues values = new ContentValues();
            values.put("nome", "Pablo Adm 1");
            values.put("matricula", "123");
            values.put("email", "pagvguerra@ig.com.br");
            values.put("senha", "123");
            values.put("contaGitHub", "pagvguerraig");
            values.put("fkCurso", 1);
            db.insert("aluno", null, values);

            //tabela curso
            values = new ContentValues();
            values.put("nome", "Java");
            db.insert("curso", null, values);

            values = new ContentValues();
            values.put("nome", "Sistemas Operacionais 1");
            db.insert("curso", null, values);

            values = new ContentValues();
            values.put("nome", "Sistemas Operacionais 2");
            db.insert("curso", null, values);

            values = new ContentValues();
            values.put("nome", "Android");
            db.insert("curso", null, values);

            values = new ContentValues();
            values.put("nome", "Engenharia de Software");
            db.insert("curso", null, values);

            //tabela aula

            //CURSO 1 - JAVA
            values = new ContentValues();
            values.put("nome", "JDBC");
            values.put("fkCurso", 1);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Herança");
            values.put("fkCurso", 1);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Interfaces");
            values.put("fkCurso", 1);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "EJB");
            values.put("fkCurso", 1);
            db.insert("aula", null, values);


            //CURSO 2 - SISTEMAS OPERACIONAIS 1
            values = new ContentValues();
            values.put("nome", "Introdução");
            values.put("fkCurso", 2);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Windows NT");
            values.put("fkCurso", 2);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Windows Server");
            values.put("fkCurso", 2);
            db.insert("aula", null, values);


            //CURSO 3 - SISTEMAS OPERACIONAIS 2
            values = new ContentValues();
            values.put("nome", "Introdução");
            values.put("fkCurso", 3);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Linux RedHat");
            values.put("fkCurso", 3);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Linux Ubuntu");
            values.put("fkCurso", 3);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Linux Fedora");
            values.put("fkCurso", 3);
            db.insert("aula", null, values);


            //CURSO 4 - ANDROID
            values = new ContentValues();
            values.put("nome", "Layouts");
            values.put("fkCurso", 4);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Broadcast Receiver");
            values.put("fkCurso", 4);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "SQLite");
            values.put("fkCurso", 4);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Intents");
            values.put("fkCurso", 4);
            db.insert("aula", null, values);


            //CURSO 5 - ENGENHARIA DE SOFTWARE
            values = new ContentValues();
            values.put("nome", "Requisitos");
            values.put("fkCurso", 5);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Regras de Negócio");
            values.put("fkCurso", 5);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "UML");
            values.put("fkCurso", 5);
            db.insert("aula", null, values);

            values = new ContentValues();
            values.put("nome", "Padrões de Desenvolvimento");
            values.put("fkCurso", 5);
            db.insert("aula", null, values);

        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
            throw e;
        } finally {
            if(db != null)
                db.close();
        }
    }

}