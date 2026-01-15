package com.EduTech.cursos.service;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.model.Inscripcion;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.CursoRepository;
import com.EduTech.cursos.repository.InscripcionRepository;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // --- 1. INSCRIBIR A UN ALUMNO EN UN CURSO ---
    public Inscripcion inscribir(Long idUsuario, Long idCurso) {
        // Buscamos al Alumno
        Usuario alumno = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Buscamos al Curso
        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Creamos la inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(alumno);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setProgreso(0.0); // Empieza en 0%

        return inscripcionRepository.save(inscripcion);
    }

    // --- 2. LISTAR CURSOS DE UN ALUMNO (Para el perfil del estudiante) ---
    public List<Inscripcion> obtenerInscripcionesPorUsuario(Long idUsuario) {
        return inscripcionRepository.findByUsuarioId(idUsuario);
    }

    // --- 3. LISTAR ALUMNOS DE UN CURSO (Para el Instructor) ---
    public List<Inscripcion> obtenerInscripcionesPorCurso(Long idCurso) {
        return inscripcionRepository.findByCursoId(idCurso);
    }
}