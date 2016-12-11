package androidjavaucam.p1android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import android.util.Log;

/**
 * Created by Pablo on 10/12/2016.
 */
public class UsuarioDAO {

    private static final String LOGCAT      =   null;
    private static String QUERY_BUSCA_ALUNO =   "SELECT _id, matricula, nome, email, senha, contaGitHub, _id, nome FROM aluno a WHERE 1 = 1 ";
    private static String QUERY_AUTH_ALUNO  =   "SELECT _id FROM aluno where email = '";

    public Usuario retornaUsuario(Cursor cursor) {
        Curso curso                         =   new Curso();
        curso.setId(cursor.getInt(6));
        curso.setNome(cursor.getString(7));

        Usuario usuario                     =   new Usuario();
        usuario.setId(cursor.getInt(0));
        usuario.setMatricula(cursor.getString(1));
        usuario.setNome(cursor.getString(2));
        usuario.setEmail(cursor.getString(3));
        usuario.setSenha(cursor.getString(4));
        usuario.setConta(cursor.getString(5));
        usuario.setCurso(curso);

        return usuario;
    }

    public Usuario autenticarUsuario(Usuario usuario, Context context) {

        SQLiteDatabase database             =   null;

        try {

            database                        =   new ConexaoSQLite(context).getReadableDatabase();
            StringBuilder   sb              =   new StringBuilder(QUERY_AUTH_ALUNO);
            sb.append(usuario.getEmail()).append("' AND senha = '").append(usuario.getSenha()).append("'");
            Cursor cursor                   =   database.rawQuery(sb.toString(), null);

            if (cursor.moveToFirst()) {
                usuario.setId(cursor.getInt(0));
            }

        } catch (SQLException e) {
            Log.d(LOGCAT, "Erro Query: ", e);
            throw e;
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
            throw e;
        } finally {
            if(database != null)
                database.close();
        }

        return usuario;
    }

    public Usuario getUsuario (String email, Context context) {

        SQLiteDatabase database             =   null;

        try {

            database                        =   new ConexaoSQLite(context).getReadableDatabase();
            StringBuilder   sb              =   new StringBuilder(QUERY_BUSCA_ALUNO);
            sb.append("AND a.email = '").append(email).append("'");
            Cursor cursor                   =   database.rawQuery(sb.toString(), null);

            if (cursor.moveToFirst()) {
                return retornaUsuario(cursor);
            }

        } catch (SQLException e) {
            Log.d(LOGCAT, "Erro Query: ", e);
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
        } finally {
            if(database != null)
                database.close();
        }

        return null;

    }

    public Usuario getUsuario (int id, Context context) {

        SQLiteDatabase database             =   null;

        try {

            database                        =   new ConexaoSQLite(context).getReadableDatabase();
            StringBuilder   sb              =   new StringBuilder(QUERY_BUSCA_ALUNO);
            sb.append("AND a._id = ").append(id);
            Cursor cursor                   =   database.rawQuery(sb.toString(), null);

            if (cursor.moveToFirst()) {
                return retornaUsuario(cursor);
            } else {
                return null;
            }

        } catch (SQLException e) {
            Log.d(LOGCAT, "Erro Query: ", e);
            throw e;
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
            throw e;
        } finally {
            if(database != null)
                database.close();
        }

    }

    public boolean atualizaUsuario(Usuario novoUsuario, Context context) {
        SQLiteDatabase database = null;
        try {
            database = new ConexaoSQLite(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome", novoUsuario.getNome());
            values.put("matricula", novoUsuario.getMatricula());
            values.put("email", novoUsuario.getEmail());
            values.put("senha", novoUsuario.getSenha());
            values.put("contaGitHub", novoUsuario.getConta());
            values.put("fkCurso", novoUsuario.getCurso().getId());
            database.update("aluno", values, "_id = " + novoUsuario.getId(), null);
            return true;
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
            throw e;
        } finally {
            if(database != null)
                database.close();
        }
    }

    public boolean cadastraUsuario(Usuario novoUsuario, Context context) {
        SQLiteDatabase database = null;
        try {
            database = new ConexaoSQLite(context).getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("nome", novoUsuario.getNome());
            values.put("matricula", novoUsuario.getMatricula());
            values.put("email", novoUsuario.getEmail());
            values.put("senha", novoUsuario.getSenha());
            values.put("contaGitHub", novoUsuario.getConta());
            values.put("fkCurso", novoUsuario.getCurso().getId());
            database.insert("aluno", null, values);
            return true;
        } catch (Exception e) {
            Log.d(LOGCAT, "Erro Generico: ", e);
            return false;
        } finally {
            if(database != null)
                database.close();
        }
    }

}