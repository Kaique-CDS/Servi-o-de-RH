package br.com.rh.model;

public class Funcionario {
    private String nome;
    private String cargo;
    private int score;

    public Funcionario(String nome, String cargo, int score) {
        this.nome = nome;
        this.cargo = cargo;
        this.score = score;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return String.format("Nome: %-15s | Cargo: %-15s | Score: %d", nome, cargo, score);
    }
}
