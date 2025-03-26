package com.cursotdd;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Armazenamento {


    public void salvar(Placar placar) throws IOException{
            Path parentDir = Paths.get(System.getProperty("user.dir")).getParent();
            
            Path filePath = parentDir.resolve("savedFile.txt");


            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            }

            String content = "a";
            Files.write(filePath, content.getBytes());
    }


    public String formatarPlacar(Placar placar){
        for(Usuario usuario : placar.getUsuarios()){
            

            


        }
        
        
        
        
        
        return "";
    }



    public String carregarDaPasta() throws IOException{
        Path parentDir = Paths.get(System.getProperty("user.dir")).getParent();
        Path filePath = parentDir.resolve("savedFile.txt");
        return new String(Files.readAllBytes(filePath));
    }

    public Placar carregar() throws IOException{
        String content = carregarDaPasta();

        return new Placar();
    }
}
