package br.com.rh.model;

public class Funcionario {
    private String nome;
    private String cargo;
    private int producao;

    public Funcionario(String nome, String cargo, int producao) {
        this.nome = nome;
        this.cargo = cargo;
        this.producao = producao;
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

    public int getProducao() {
        return producao;
    }

    public void setProducao(int producao) {
        this.producao = producao;
    }

    @Override
    public String toString() {
        return String.format("Nome: %-15s | Cargo: %-15s | Produção: %d", nome, cargo, producao);
    }
}
