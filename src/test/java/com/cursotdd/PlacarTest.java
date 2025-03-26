package com.cursotdd;

import java.io.IOException;
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
    }
    
    @Test
    public void deveIniciarComZeroUsuarios() {
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.isEmpty();
    }

    @Test
    public void deveAdicionarUmUsuario() throws IOException {
        placar.adicionarUsuario(new Usuario("Sergio"));
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.size() == 1;
    }
    @Test
    public void deveAdicionarVinteUsuarios() throws IOException {
        for (int i = 0; i < 20; i++) {
            placar.adicionarUsuario(new Usuario(Integer.toString(i)));
        }
        List<Usuario> usuarios = placar.getUsuarios();
        assert usuarios.size() == 20;
    }

    @Test
    public void deveRegistrarPontosAUsuario() throws IOException{
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
    public void deveRetornarPlacarPorPonto() throws IOException
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

    @Test
    public void deveRetornarPontosDoUsuario() throws IOException
    {
        placar.adicionarUsuario(new Usuario("Sergio"));
        placar.adicionarPontoAUsuario("a",  "Sergio");
        placar.adicionarPontoAUsuario("b",  "Sergio");
        placar.adicionarPontoAUsuario("c",  "Sergio");
        placar.adicionarPontoAUsuario("didi",  "Sergio");
        placar.adicionarPontoAUsuario("e",  "Sergio");
        placar.adicionarPontoAUsuario("f",  "Sergio");

        List<Ponto> pontos = placar.pontosDoUsuario("Sergio");
        assert pontos.size() == 6; 
        assert pontos.stream().anyMatch(ponto -> ponto.getTipo().equals("didi"));
    }
}
