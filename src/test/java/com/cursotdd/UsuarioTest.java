package com.cursotdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UsuarioTest {
    private Usuario usuario;
    
    @BeforeEach
    public void iniciar(){
        usuario = new Usuario("Sergio");
    }


    @Test
    public void devePoderReceberPontos(){
        usuario.receberPonto("A");

        assert usuario.getPontos().size() == 1;
    }

    @Test
    public void devePoderReceberVariosPontosDoMesmoTipo(){

        for (int i = 0; i < 10; i++) {   
            usuario.receberPonto("a");
        }

        assert usuario.getPontos().size() == 1;
        assert usuario.getPontos().get(0).getCount() == 10;
    }

    @Test
    public void devePoderReceberVariosPontosDiferentes(){
        
        for (int i = 0; i < 10; i++) {   
            usuario.receberPonto(Integer.toString(i));
        }

        assert usuario.getPontos().size() == 10;
        assert usuario.getPontos().get(0).getCount() == 1;
    }
}
