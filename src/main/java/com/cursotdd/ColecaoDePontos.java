package com.cursotdd;
import java.util.ArrayList;
import java.util.List;

public class ColecaoDePontos {
    private final List<Ponto> pontos = new ArrayList<>();
    
    public List<Ponto> getPontos() {
        return pontos;
    }

    public void receberPonto(String tipo){
        for (Ponto p : pontos) {
            if(p.getTipo().equals(tipo)){
                p.incrementCount();
                return;
            }
        }

        pontos.add(new Ponto(tipo));
    }
}
