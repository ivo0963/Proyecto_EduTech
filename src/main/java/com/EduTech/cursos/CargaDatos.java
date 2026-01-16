package com.EduTech.cursos;

import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
public class CargaDatos implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() > 0) {
            System.out.println("ℹ️ La base de datos ya tiene usuarios.");
            return;
        }

        System.out.println("⏳ Creando usuarios manualmente...");

        // --- 1. GERENTE ---
        Usuario u1 = new Usuario();
        u1.setNombre("Carlos Gerente");
        u1.setEmail("gerente@edutech.com");
        u1.setPassword("admin123");
        u1.setRol("GERENTE");
        usuarioRepository.save(u1);

        // --- 2. INSTRUCTOR ---
        Usuario u2 = new Usuario();
        u2.setNombre("Profesor Java");
        u2.setEmail("profe@edutech.com");
        u2.setPassword("123456");
        u2.setRol("INSTRUCTOR");
        usuarioRepository.save(u2);

        // --- 3. ESTUDIANTE ---
        Usuario u3 = new Usuario();
        u3.setNombre("Alumno Curioso");
        u3.setEmail("alumno@edutech.com");
        u3.setPassword("abcdef");
        u3.setRol("ESTUDIANTE");
        usuarioRepository.save(u3);

        System.out.println("✅ ¡Listo! 3 Usuarios creados.");

        // Imprimir en consola para verificar (como un SELECT *)
        usuarioRepository.findAll().forEach(u ->
                System.out.println("Usuario guardado: ID=" + u.getId() + " | " + u.getNombre())
        );
    }
}