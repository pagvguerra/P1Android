package androidjavaucam.p1android;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Pablo on 10/12/2016.
 */
public class ConexaoSQLite extends SQLiteOpenHelper {

    private static final String NOME_DO_BANCO = "BANCO_DE_DADOS2_ANDROID_P2";
    private static final int VERSAO_BANCO = 1;

    private Context contexto;

    public ConexaoSQLite(Context context) {
        super(context, NOME_DO_BANCO, null, VERSAO_BANCO);
        this.contexto = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
