package androidjavaucam.p1android;

import java.io.Serializable;

/**
 * Created by Pablo on 10/12/2016.
 */
public class Aula implements Serializable {
    public int id;
    public String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
