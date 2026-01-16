package com.EduTech.cursos.repository;

import com.EduTech.cursos.model.Resena;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ResenaRepository extends JpaRepository<Resena, Long> {

    List<Resena> findByCursoId(Long cursoId);

    List<Resena> findByUsuarioId(Long usuarioId);
}