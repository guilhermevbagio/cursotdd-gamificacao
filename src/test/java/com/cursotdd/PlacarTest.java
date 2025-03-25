package com.cursotdd;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PlacarTest {

    Placar placar;

    @BeforeEach
    public void iniciarPlacar() {
        placar = new Placar();
        placar.iniciar();
    }
    
    @Test
    public void deveIniciarComZeroUsuarios() {
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.isEmpty();
    }

    @Test
    public void deveAdicionarUmUsuario() {
        placar.adicionarUsuario(new Usuario("Sergio"));
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.size() == 1;
    }
    @Test
    public void deveAdicionarVinteUsuarios(){
        for (int i = 0; i < 20; i++) {
            placar.adicionarUsuario(new Usuario(Integer.toString(i)));
        }
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.size() == 20;
    }

    @Test
    public void deveRegistrarPontosAUsuario(){
        Usuario usuario = new Usuario("Sergio");
        placar.adicionarUsuario(usuario);
        placar.adicionarPontoAUsuario("a", "Sergio");
        assert placar.getUsuario("Sergio").getPontos().size() == 1;
    }

    @Test
    @SuppressWarnings("")
    public void deveLancarErroAoNaoAcharUsuario(){
        assertThrows(RuntimeException.class, () -> placar.adicionarPontoAUsuario("a", "Sergio"));
    }
    
    @Test
    public void deveRetornarPlacarPorPonto()
    {
        //cria 20 usuarios
        for(int j = 0; j < 20; j++){
            placar.adicionarUsuario(new Usuario(Integer.toString(j)));
        }
        //atribui pontos aleatoriamente
        for(int k = 0; k < 100; k++){
            int nomeDoido = ThreadLocalRandom.current().nextInt(0, 20);

            placar.adicionarPontoAUsuario("a",  Integer.toString(nomeDoido));
        }

        List<Usuario> ranking = placar.rankingPorPonto("a");

        for(int i = 0; i < ranking.size() - 1; i++){
            assert (  ranking.get(i).getPonto("a").getCount() >= ranking.get(i + 1).getPonto("a").getCount()  );
        }
    }
}
