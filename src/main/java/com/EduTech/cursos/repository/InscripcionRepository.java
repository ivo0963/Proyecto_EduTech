package com.EduTech.cursos.repository;

import com.EduTech.cursos.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // Buscar todas las inscripciones de un alumno específico
    List<Inscripcion> findByUsuarioId(Long usuarioId);

    // Buscar todas las inscripciones de un curso específico (Para que el instructor vea sus alumnos)
    List<Inscripcion> findByCursoId(Long cursoId);
}