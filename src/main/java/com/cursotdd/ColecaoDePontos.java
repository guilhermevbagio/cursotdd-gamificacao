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
    public void receberPonto(Ponto ponto){
        for (Ponto p : pontos) {
            if(p.getTipo().equals(ponto.getTipo())){
                p.sumCount(ponto.getCount());
                return;
            }
        }

        pontos.add(ponto);
    }
}
