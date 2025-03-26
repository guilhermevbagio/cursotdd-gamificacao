package com.cursotdd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Placar {
    
    //TODO passar esse usuarios para o armazenamento
    List<Usuario> usuarios = new ArrayList<>();
    private static final Armazenamento armazenamento = new Armazenamento();

    public Placar(){

    }

    public Placar(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuario> getUsuarios() { 
        return usuarios; 
    };

    public void adicionarUsuario(Usuario usuario) throws IOException {
        usuarios.add(usuario);
        armazenamento.salvar(this);
    };

    public Usuario getUsuario(String nome){
        return usuarios.stream()
            .filter(u -> u.getNome().equals(nome))
            .findFirst()
            .orElse(null);
    }

    public void adicionarPontoAUsuario(String ponto, String nome) throws IOException {
        for(Usuario usuario : usuarios){
            if(usuario.getNome().equals(nome)){
                usuario.receberPonto(ponto);
                armazenamento.salvar(this);
                return;
            }
        }

        throw new RuntimeException("Usuário não encontrado");
    }

    public List<Ponto> pontosDoUsuario(String nome){
        return usuarios.stream()
            .filter(u -> u.getNome().equals(nome))
            .findFirst()
            .map(u -> u.getPontos())
            .orElse(null);
    }

    public List<Usuario> rankingPorPonto(String ponto){
        List<Usuario> lista = new ArrayList<>();

        for(Usuario usuario : usuarios){
            if(usuario.getPonto(ponto) != null){
                lista.add(usuario);
            }
        }

        lista.sort((a, b) -> Integer.compare(b.getPonto(ponto).getCount(), a.getPonto(ponto).getCount()));
        return lista;
    }
}
