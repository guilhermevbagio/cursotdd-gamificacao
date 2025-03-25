package com.cursotdd;

public class Ponto {
    private final String tipo;
    private int count;


    public Ponto(String tipo){
        this.count = 1;
        this.tipo = tipo;
    }

    public int getCount() {
        return count;
    }

    public String getTipo(){
        return tipo;
    }

    public void incrementCount() {
        count++;
    }
    
    public void decrementCount() {
        count--;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
