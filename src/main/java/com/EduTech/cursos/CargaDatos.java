package com.EduTech.cursos;

import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaDatos implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() > 0) {
            System.out.println("ℹ️ La base de datos ya tiene usuarios. No se cargarán nuevos.");
            return;
        }

        System.out.println("⏳ Generando 10 usuarios de prueba...");

        crearUsuario("Carlos Gerente", "admin@edutech.com", "admin123", "GERENTE");

        crearUsuario("Profe Java", "java@edutech.com", "123456", "INSTRUCTOR");
        crearUsuario("Profe Python", "python@edutech.com", "123456", "INSTRUCTOR");
        crearUsuario("Profe Web", "web@edutech.com", "123456", "INSTRUCTOR");

        for (int i = 1; i <= 6; i++) {
            crearUsuario(
                    "Alumno " + i,
                    "alumno" + i + "@edutech.com",
                    "pass123",
                    "ESTUDIANTE"
            );
        }

        System.out.println("✅ ¡Carga completa!");

        // Imprimir resumen en consola
        System.out.println("--- LISTA DE USUARIOS EN BD ---");
        usuarioRepository.findAll().forEach(u ->
                System.out.println("ID: " + u.getId() + " | ROL: " + u.getRol() + " | " + u.getNombre() + " (" + u.getEmail() + ")")
        );
    }

    private void crearUsuario(String nombre, String email, String password, String rol) {
        Usuario u = new Usuario();
        u.setNombre(nombre);
        u.setEmail(email);
        u.setPassword(password);
        u.setRol(rol);
        usuarioRepository.save(u);
    }
}