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

    public Inscripcion inscribir(Long idUsuario, Long idCurso) {
        Usuario alumno = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        Curso curso = cursoRepository.findById(idCurso)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setUsuario(alumno);
        inscripcion.setCurso(curso);
        inscripcion.setFechaInscripcion(LocalDate.now());
        inscripcion.setProgreso(0.0);

        return inscripcionRepository.save(inscripcion);
    }

    public List<Inscripcion> obtenerInscripcionesPorUsuario(Long idUsuario) {
        return inscripcionRepository.findByUsuarioId(idUsuario);
    }

    public List<Inscripcion> obtenerInscripcionesPorCurso(Long idCurso) {
        return inscripcionRepository.findByCursoId(idCurso);
    }
    public Inscripcion obtenerPorId(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con ID: " + id));
    }

    public Inscripcion actualizarProgreso(Long idInscripcion, Double nuevoPorcentaje) {
        Inscripcion inscripcion = inscripcionRepository.findById(idInscripcion)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada"));

        if (nuevoPorcentaje < 0 || nuevoPorcentaje > 100) {
            throw new RuntimeException("El progreso debe estar entre 0 y 100");
        }

        inscripcion.setProgreso(nuevoPorcentaje);
        return inscripcionRepository.save(inscripcion);
    }
}