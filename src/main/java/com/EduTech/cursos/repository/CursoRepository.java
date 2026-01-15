package com.EduTech.cursos.repository;

import com.EduTech.cursos.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // <--- Importante que esté este import

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {

    // ESTA ES LA LÍNEA QUE TE FALTA:
    // Spring crea la consulta automáticamente al leer "findByIdInstructor"
    List<Curso> findByIdInstructor(Long idInstructor);
}