package com.cursotdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MockArmazenamento implements Armazenamento {

    @Override
    public void salvar(Placar placar) throws IOException {
        
    }

    @Override
    public void limpar() throws IOException {
        
    }

    @Override
    public Placar carregar() throws IOException {
        Placar placar = new Placar();
        placar.setArmazenamento(this);
        return placar;
    }

    @Override
    public List<Usuario> carregarUsuarios() throws IOException {
        String[] nomes = {
            "Alice", "Bruno", "Carlos", "Daniel", "Eduarda", 
            "Fernanda", "Gabriel", "Helena", "Igor", "Joana",
            "Karla", "Luiz", "Maria", "Nicolas", "Olga", 
            "Paula", "Quiteria", "Rafael", "Sofia", "Tiago"
        };

        List<Usuario> usuarios = new ArrayList<>();

        for(int j = 0; j < 20; j++){
            usuarios.add(new Usuario(nomes[j]));
        }

        return usuarios;
    }

    
    
}
