package com.cursotdd;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class ArmazenamentoTest {
    private Armazenamento armazenamento;

    String[] nomes = {
        "Alice", "Bruno", "Carlos", "Daniel", "Eduarda", 
        "Fernanda", "Gabriel", "Helena", "Igor", "Joana",
        "Karla", "Luiz", "Maria", "Nicolas", "Olga", 
        "Paula", "Quiteria", "Rafael", "Sofia", "Tiago"
    };

    String[] medalhas = {
        "Estrela", "Coração", "Biscoito", "Raios", "Luar", 
        "Diamante", "Ouro", "Prata", "Bronze", "Cristal"
    };

    @BeforeEach
    public void iniciar(){
        armazenamento = new Armazenamento();
    }

    @Test
    public void deveSalvarDados() throws IOException{
        Placar placar = new Placar();

                //cria 20 usuarios
        for(int j = 0; j < 20; j++){
            placar.adicionarUsuario(new Usuario(nomes[j]));
        }
        //atribui pontos aleatoriamente
        for(int k = 0; k < 100; k++){
            int nomeDoido = ThreadLocalRandom.current().nextInt(0, 20);

            placar.adicionarPontoAUsuario(medalhas[k % 10],  nomes[nomeDoido]);
        }


        armazenamento.salvar(placar);
    }

    @Test
    public void deveCarregarDados() throws IOException{
        armazenamento.carregar();
    }

    @Test
    public void dadosCarregadosDevemSerIguaisOsSalvos() throws IOException{

        Placar placar = new Placar();

        //cria 20 usuarios
        for(int j = 0; j < 20; j++){
            placar.adicionarUsuario(new Usuario(nomes[j]));
        }
        //atribui pontos aleatoriamente
        for(int k = 0; k < 100; k++){
            int nomeDoido = ThreadLocalRandom.current().nextInt(0, 20);

            placar.adicionarPontoAUsuario(medalhas[k % 10],  nomes[nomeDoido]);
        }


        armazenamento.salvar(placar);


        Placar temp = armazenamento.carregar();

        for(int i = 0; i < placar.getUsuarios().size(); i++){
            for(int j = 0; j < placar.getUsuarios().get(i).getPontos().size(); j++){
                assert placar.getUsuarios().get(i).getPontos().get(j).getCount() == temp.getUsuarios().get(i).getPontos().get(j).getCount();
            }
        }
    }

    @Test
    public void dadosCarregadosDevemSerIguaisOsSalvos2() throws IOException{

        Placar placar = new Placar();

        //cria 100 usuarios
        for(int j = 0; j < 20; j++){
            placar.adicionarUsuario(new Usuario(nomes[j]));
        }
        //atribui pontos aleatoriamente
        for(int k = 0; k < 1000; k++){
            int nomeDoido = ThreadLocalRandom.current().nextInt(0, 20);

            placar.adicionarPontoAUsuario(medalhas[k % 10],  nomes[nomeDoido]);
        }


        Placar temp = armazenamento.carregar();

        for(int i = 0; i < 20; i++){
            for(int j = 0; j < placar.getUsuarios().get(i).getPontos().size(); j++){
                assert placar.getUsuarios().get(i).getPontos().get(j).getCount() == temp.getUsuarios().get(i).getPontos().get(j).getCount();
            }
        }
    }

    @Test
    public void deveRetornarPontosDoUsuario() throws IOException
    {
        Placar placar = new Placar();
        placar.adicionarUsuario(new Usuario("Sergio"));
        placar.adicionarPontoAUsuario("a",  "Sergio");
        placar.adicionarPontoAUsuario("b",  "Sergio");
        placar.adicionarPontoAUsuario("c",  "Sergio");
        placar.adicionarPontoAUsuario("didi",  "Sergio");
        placar.adicionarPontoAUsuario("e",  "Sergio");
        placar.adicionarPontoAUsuario("f",  "Sergio");

        List<Ponto> pontos = armazenamento.carregarPontosDoUsuario("Sergio");
        assert pontos.size() == 6; 
        assert pontos.stream().anyMatch(ponto -> ponto.getTipo().equals("didi"));
    }

    @Test
    public void deveRetornarTodosUsuariosComPontos() throws IOException
    {
        Placar placar = new Placar();
        placar.adicionarUsuario(new Usuario("Sergio"));
        placar.adicionarUsuario(new Usuario("Alan"));
        placar.adicionarUsuario(new Usuario("Marcelo"));


        placar.adicionarPontoAUsuario("a",  "Sergio");
        placar.adicionarPontoAUsuario("b",  "Sergio");
        placar.adicionarPontoAUsuario("c",  "Alan");

        assert armazenamento.carregaTodUsuariosComAlgumPonto().stream().filter(usuario -> usuario.getNome().equals("Sergio") || usuario.getNome().equals("Alan")).count() == 2;
        assert armazenamento.carregaTodUsuariosComAlgumPonto().stream().filter(usuario -> usuario.getNome().equals("Marcelo")).count() == 0;

    }

    @Test
    public void deveRetornarQuantosPontosDoTipoUmUsuarioTem() throws IOException
    {
        Placar placar = new Placar();
        placar.adicionarUsuario(new Usuario("Sergio"));
        placar.adicionarPontoAUsuario("didi",  "Sergio");
        placar.adicionarPontoAUsuario("didi",  "Sergio");
        placar.adicionarPontoAUsuario("didi",  "Sergio");

        placar.adicionarPontoAUsuario("f",  "Sergio");
        placar.adicionarPontoAUsuario("g",  "Sergio");
        placar.adicionarPontoAUsuario("f",  "Sergio");



        assert placar.getUsuario("Sergio").getPontos().size() == 3;
        assert placar.getUsuario("Sergio").getPonto("didi").getCount() == 3;


        placar.adicionarPontoAUsuario("amor",  "Sergio");

        assert placar.getUsuario("Sergio").getPontos().size() == 4;



        assert armazenamento.carregarQuantidadeDePontosDoTipoUsuarioTem("b", "Sergio") == 0;
        assert armazenamento.carregarQuantidadeDePontosDoTipoUsuarioTem("didi", "Sergio") == 3;
        assert armazenamento.carregarQuantidadeDePontosDoTipoUsuarioTem("amor", "Sergio") == 1;
    }

}
