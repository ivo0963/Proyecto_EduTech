package com.EduTech.cursos;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.model.Inscripcion;
import com.EduTech.cursos.model.Material;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.UsuarioRepository;
import com.EduTech.cursos.service.CursoService;
import com.EduTech.cursos.service.InscripcionService;
import com.EduTech.cursos.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimulacionCompleta implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private CursoService cursoService;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private InscripcionService inscripcionService;

    @Override
    public void run(String... args) throws Exception {
        Thread.sleep(1000);

        System.out.println("\n=================================================");
        System.out.println("🎬 INICIANDO SIMULACIÓN DE CLASES Y MATRÍCULA");
        System.out.println("=================================================\n");

        Usuario profe = usuarioRepository.findByEmail("profe@edutech.com").orElse(null);
        Usuario alumno = usuarioRepository.findByEmail("alumno@edutech.com").orElse(null);

        if (profe == null || alumno == null) {
            System.out.println("❌ Error: No se encontraron los usuarios. Asegúrate de que CargaDatos se ejecutó.");
            return;
        }

        System.out.println("✅ Actores listos:");
        System.out.println("   - Instructor: " + profe.getNombre());
        System.out.println("   - Estudiante: " + alumno.getNombre());

        System.out.println("\n👨‍🏫 1. El profesor está creando el curso 'Master en Java'...");

        Curso curso = new Curso();
        curso.setTitulo("Master en Java Spring Boot");
        curso.setDescripcion("Curso completo de Backend 2026");
        curso.setIdInstructor(profe.getId());

        Curso cursoGuardado = cursoService.guardarCurso(curso);

        cursoService.aprobarCurso(cursoGuardado.getId());
        System.out.println("   ✅ Curso creado y APROBADO con ID: " + cursoGuardado.getId());

        System.out.println("\n📚 2. Subiendo material didáctico...");
        Material mat1 = new Material(null, "Clase 1: Introducción", "Video Bienvenida", "http://youtube.com/v1", cursoGuardado);
        Material mat2 = new Material(null, "Clase 2: Beans y Componentes", "PDF Teórico", "http://drive.com/pdf", cursoGuardado);

        materialService.agregarMaterial(cursoGuardado.getId(), mat1);
        materialService.agregarMaterial(cursoGuardado.getId(), mat2);

        System.out.println("   ✅ Materiales cargados al curso.");

        System.out.println("\n🎓 3. El alumno se está inscribiendo...");

        try {
            Inscripcion inscripcion = inscripcionService.inscribir(alumno.getId(), cursoGuardado.getId());
            System.out.println("   🎉 ¡INSCRIPCIÓN EXITOSA!");
            System.out.println("      Alumno: " + inscripcion.getUsuario().getNombre());
            System.out.println("      Curso: " + inscripcion.getCurso().getTitulo());
            System.out.println("      Progreso: " + inscripcion.getProgreso() + "%");
        } catch (Exception e) {
            System.out.println("   ⚠️ El alumno ya estaba inscrito o hubo un error: " + e.getMessage());
        }

        System.out.println("\n=================================================");
        System.out.println("🚀 SIMULACIÓN FINALIZADA");
        System.out.println("=================================================\n");
    }
}