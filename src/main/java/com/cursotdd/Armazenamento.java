package com.cursotdd;
import java.io.IOException;
import java.util.List;

public interface Armazenamento {
    
    public void limpar() throws IOException;
    public void salvar(Placar placar) throws IOException;
    public Placar carregar() throws IOException;
    public List<Usuario> carregarUsuarios() throws IOException;

}
