package com.EduTech.cursos.service;

import com.EduTech.cursos.model.Curso;
import com.EduTech.cursos.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso guardarCurso(Curso curso) {
        if (curso.getEstado() == null || curso.getEstado().isEmpty()) {
            curso.setEstado("PENDIENTE");
        }
        return cursoRepository.save(curso);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso obtenerPorId(Long id) {
        return cursoRepository.findById(id).orElse(null);
    }

    public List<Curso> listarCursosPorInstructor(Long idInstructor) {
        return cursoRepository.findByInstructorId(idInstructor);
    }

    public Curso aprobarCurso(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        curso.setEstado("APROBADO");
        return cursoRepository.save(curso);
    }

    public Curso asignarInstructor(Long cursoId, Long instructorId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        curso.setInstructorId(instructorId);

        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}