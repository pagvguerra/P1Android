package androidjavaucam.p1android;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 11/12/2016.
 */
public class CursoDAO {

    private static final String LOGCAT      =   null;
    private static final String QUERY_BUSCA_CURSO = "select _id, nome from curso";

    public List<String> listaCursos(Context context){

        List<String> listaCurso             =   new ArrayList<String>();
        SQLiteDatabase database             =   null;

        try {

            database                        =   new ConexaoSQLite(context).getReadableDatabase();
            StringBuilder   sb              =   new StringBuilder(QUERY_BUSCA_CURSO);
            Cursor cursor                   =   database.rawQuery(sb.toString(), null);

            if (cursor.moveToFirst()) {
                do {
                    listaCurso.add(cursor.getString(1));
                } while (cursor.moveToNext());
            }

        } catch (SQLException e) {
            Log.d(LOGCAT, "Erro Query: ", e);
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
        } finally {
            if(database != null)
                database.close();
        }

        return listaCurso;
    }

}
