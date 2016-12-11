package androidjavaucam.p1android;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

/**
 * Created by Pablo on 11/12/2016.
 */
public class TarefaDAO {

    private static final String LOGCAT      =   null;

    public void cadastrarTarefa(Tarefa tarefa, Context context) {
        SQLiteDatabase database = null;
        try {
            database = new ConexaoSQLite(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("fkAluno", tarefa.getFkAluno());
            values.put("fkCurso", tarefa.getFkCurso());
            values.put("fkAula", tarefa.getFkAula());
            values.put("exercicioFeito", tarefa.isExercicioFeito());
            values.put("laboratorioEnviado", tarefa.isLaboratorioEnviado());
            database.insert("tarefa", null, values);
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
        } finally {
            if(database != null)
                database.close();
        }
    }

}