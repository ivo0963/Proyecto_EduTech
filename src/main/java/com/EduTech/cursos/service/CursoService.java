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
        if (curso.getEstado() == null) curso.setEstado("PENDIENTE");
        return cursoRepository.save(curso);
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    public Curso aprobarCurso(Long id) {
        Curso curso = cursoRepository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
        curso.setEstado("APROBADO");
        return cursoRepository.save(curso);
    }

    public Curso asignarInstructor(Long cursoId, Long instructorId) {
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new RuntimeException("No encontrado"));
        curso.setIdInstructor(instructorId);
        return cursoRepository.save(curso);
    }
}