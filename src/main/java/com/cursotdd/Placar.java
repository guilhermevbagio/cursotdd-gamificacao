package com.cursotdd;
import java.util.ArrayList;
import java.util.List;

public class Placar {
    List<Usuario> usuarios = new ArrayList<>();
    
    public void iniciar() { };

    public List<Usuario> getUsuarios() { 
        return usuarios; 
    };

    public void adicionarUsuario(Usuario usuario) {
        usuarios.add(usuario);
    };

    public Usuario getUsuario(String nome){
        return usuarios.stream()
            .filter(u -> u.getNome().equals(nome))
            .findFirst()
            .orElse(null);
    }

    public void adicionarPontoAUsuario(String ponto, String nome){
        for(Usuario usuario : usuarios){
            if(usuario.getNome().equals(nome)){
                usuario.receberPonto(ponto);
                return;
            }
        }

        throw new RuntimeException("Usuário não encontrado");
    }

    public String pontosDoUsuario(String nome){
        return nome;
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
