package com.cursotdd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PlacarArmazenamentoPlainTextIntegrationTest {

    @Test
    public void testSalvarPlacar() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Sergio"));
        usuarios.add(new Usuario("Maria"));
        Placar placar = new Placar(usuarios);

        ArmazenamentoPlainText armazenamento = new ArmazenamentoPlainText();

        armazenamento.salvar(placar);

        Placar savedPlacar = armazenamento.carregar();
        assertNotNull(savedPlacar);
        List<Usuario> savedUsuarios = savedPlacar.getUsuarios();
        assertEquals(usuarios.size(), savedUsuarios.size());
        for (int i = 0; i < usuarios.size(); i++) {
            assertEquals(usuarios.get(i).getNome(), savedUsuarios.get(i).getNome());
        }
    }

    @Test
    public void testCarregarPlacar() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Sergio"));
        usuarios.add(new Usuario("Maria"));
        Placar placar = new Placar(usuarios);

        ArmazenamentoPlainText armazenamento = new ArmazenamentoPlainText();

        armazenamento.salvar(placar);

        Placar loadedPlacar = armazenamento.carregar();
        assertNotNull(loadedPlacar);
        List<Usuario> loadedUsuarios = loadedPlacar.getUsuarios();
        assertEquals(usuarios.size(), loadedUsuarios.size());
        for (int i = 0; i < usuarios.size(); i++) {
            assertEquals(usuarios.get(i).getNome(), loadedUsuarios.get(i).getNome());
        }
    }

    @Test
    public void testLimparPlacar() throws IOException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Sergio"));
        usuarios.add(new Usuario("Maria"));
        Placar placar = new Placar(usuarios);

        ArmazenamentoPlainText armazenamento = new ArmazenamentoPlainText();

        armazenamento.salvar(placar);

        armazenamento.limpar();

        Placar clearedPlacar = armazenamento.carregar();
        assertTrue(clearedPlacar.getUsuarios().isEmpty());
    }
}