package androidjavaucam.p1android;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pablo on 11/12/2016.
 */
public class AulaDAO {

    private static final String LOGCAT      =   null;
    private static final String QUERY_BUSCA_AULA = "select _id, nome from aula where fkCurso = ";

    public List<String> listaAulas(Context context, int curso){

        List<String> listaAulas             =   new ArrayList<String>();
        SQLiteDatabase database             =   null;

        try {

            database                        =   new ConexaoSQLite(context).getReadableDatabase();
            StringBuilder   sb              =   new StringBuilder(QUERY_BUSCA_AULA);
            sb.append(curso);
            Cursor cursor                   =   database.rawQuery(sb.toString(), null);

            if (cursor.moveToFirst()) {
                do {
                    listaAulas.add(cursor.getString(1));
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

        return listaAulas;
    }

}
