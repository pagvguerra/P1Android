package androidjavaucam.p1android;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Pablo on 10/12/2016.
 */
public class Curso implements Serializable {
    public int id;
    public String nome;
    public List<Aula> aula;

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

    public List<Aula> getAula() {
        return aula;
    }

    public void setAula(List<Aula> aula) {
        this.aula = aula;
    }
}
