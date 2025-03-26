package com.cursotdd;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ArmazenamentoTest {
    private Armazenamento armazenamento;

    @BeforeEach
    public void iniciar(){
        armazenamento = new Armazenamento();
    }

    @Test
    public void deveSalvarDados() throws IOException{
        Placar placar = new Placar();
        armazenamento.salvar(placar);
    }

    @Test
    public void deveCarregarDados() throws IOException{
        armazenamento.carregar();
    }
}
