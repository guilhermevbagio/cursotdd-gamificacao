package com.cursotdd;
import java.util.List;

public class Usuario {
    private final String nome;
    private final ColecaoDePontos colecao = new ColecaoDePontos();

    public Usuario(String nome){
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Ponto getPonto(String arg) {
        return colecao.getPontos().stream()
                .filter(ponto -> ponto.getTipo().equals(arg))
                .findFirst()
                .orElse(null);
    }
    public List<Ponto> getPontos(){
        return colecao.getPontos();
    }

    public void receberPonto(String ponto){
        colecao.receberPonto(ponto);
    }

    public void receberPonto(Ponto ponto){
        colecao.receberPonto(ponto);
    }
}
