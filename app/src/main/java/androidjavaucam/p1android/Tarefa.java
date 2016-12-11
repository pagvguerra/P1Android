package androidjavaucam.p1android;

/**
 * Created by Pablo on 11/12/2016.
 */
public class Tarefa {

    private int fkAluno;
    private int fkCurso;
    private int fkAula;
    private boolean exercicioFeito;
    private boolean laboratorioEnviado;

    public int getFkAluno() {
        return fkAluno;
    }

    public void setFkAluno(int fkAluno) {
        this.fkAluno = fkAluno;
    }

    public int getFkCurso() {
        return fkCurso;
    }

    public void setFkCurso(int fkCurso) {
        this.fkCurso = fkCurso;
    }

    public int getFkAula() {
        return fkAula;
    }

    public void setFkAula(int fkAula) {
        this.fkAula = fkAula;
    }

    public boolean isExercicioFeito() {
        return exercicioFeito;
    }

    public void setExercicioFeito(boolean exercicioFeito) {
        this.exercicioFeito = exercicioFeito;
    }

    public boolean isLaboratorioEnviado() {
        return laboratorioEnviado;
    }

    public void setLaboratorioEnviado(boolean laboratorioEnviado) {
        this.laboratorioEnviado = laboratorioEnviado;
    }
}
