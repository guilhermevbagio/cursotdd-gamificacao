package com.cursotdd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class ArmazenamentoPlainText implements Armazenamento{

    @Override
    public void limpar() throws IOException{
        Path parentDir = Paths.get(System.getProperty("user.dir")).getParent();
        Path filePath = parentDir.resolve("savedFile.txt");
        Files.deleteIfExists(filePath);
    }
    @Override
    public void salvar(Placar placar) throws IOException{
            Path parentDir = Paths.get(System.getProperty("user.dir")).getParent();
            
            Path filePath = parentDir.resolve("savedFile.txt");


            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

            String content = formatarPlacar(placar);
            Files.write(filePath, content.getBytes());
    }


    public String formatarPlacar(Placar placar){
        String serial = "";

        serial += "________________\n\n";

        for(Usuario usuario : placar.getUsuarios()){
            String usuarioSerial = "";
            
            usuarioSerial += "Usuario: " + usuario.getNome() + "\n";

            usuarioSerial += " Pontos: \n";
            for(Ponto ponto : usuario.getPontos()){
                usuarioSerial += "--> " + ponto.getTipo() + " : " + ponto.getCount() + "\n";
            }
            serial += usuarioSerial  + "________________\n\n";
        }

        return serial;
    }

    public String carregarDaPasta() throws IOException{
        try {
            
            Path parentDir = Paths.get(System.getProperty("user.dir")).getParent();
            Path filePath = parentDir.resolve("savedFile.txt");
            return new String(Files.readAllBytes(filePath));
        } catch (Exception e) {
            return "";
        }
    }

    @Override
    public Placar carregar() throws IOException {
        return new Placar(carregarUsuarios());
    }

    @Override
    public List<Usuario> carregarUsuarios() throws IOException {
        String content = carregarDaPasta();
        List<Usuario> usuarios = new ArrayList<>();
    
        String[] split = content.split("________________");
    

        for (int i = 1; i < split.length; i++) { // Bloco de Usuario
            
            String s = split[i].trim();
            if (s.isEmpty()) continue;
            
            String[] lines = s.split("\n");
            String nome = lines[0].split(": ")[1].trim();

            Usuario usuario = new Usuario(nome);

            
            for(int j = 2; j < lines.length; j++){
                String linhaLimpa = lines[j].substring(3);
                String tipo = linhaLimpa.split(": ")[0].trim();
                int count = Integer.parseInt(linhaLimpa.split(": ")[1].trim());
                Ponto ponto = new Ponto(tipo);
                ponto.setCount(count);
                usuario.receberPonto(ponto);
            }

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public List<Ponto> carregarPontosDoUsuario(String nome) throws IOException {
        
        Placar temp = carregar();

        return temp.pontosDoUsuario(nome);
    }

    public List<Usuario> carregaTodUsuariosComAlgumPonto() throws IOException {
        Placar temp = carregar();
        
        List<Usuario> ret = new ArrayList<>();

        for(Usuario usuario : temp.getUsuarios()){
            if(usuario.getPontos().isEmpty()) continue;

            ret.add(usuario);
        }

        return ret;
    }

    public List<String> carregarTiposDePontoRegistrados() throws IOException {
        Placar temp = carregar();

        List<String> ret = new ArrayList<>();

        for(Usuario usuario : temp.getUsuarios()){
            for(Ponto ponto : usuario.getPontos()){
                if(!ret.contains(ponto.getTipo())) ret.add(ponto.getTipo());
            }
        }

        return ret;
    }

    public int carregarQuantidadeDePontosDoTipoUsuarioTem(String tipo, String usuario) throws IOException {
        Placar temp = carregar();

        Ponto ponto = temp.getUsuario(usuario).getPonto(tipo);

       return ponto != null ? ponto.getCount() : 0; 
    }
    
}
