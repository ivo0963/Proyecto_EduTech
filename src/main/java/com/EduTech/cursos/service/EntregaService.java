package com.EduTech.cursos.service;

import com.EduTech.cursos.model.Entrega;
import com.EduTech.cursos.model.Evaluacion;
import com.EduTech.cursos.model.Usuario;
import com.EduTech.cursos.repository.EntregaRepository;
import com.EduTech.cursos.repository.EvaluacionRepository;
import com.EduTech.cursos.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    @Autowired private EntregaRepository entregaRepository;
    @Autowired private EvaluacionRepository evaluacionRepository;
    @Autowired private UsuarioRepository usuarioRepository;

    public Entrega enviarRespuesta(Long idEvaluacion, Long idEstudiante, String respuestaTexto) {
        Evaluacion evaluacion = evaluacionRepository.findById(idEvaluacion)
                .orElseThrow(() -> new RuntimeException("Evaluación no encontrada"));

        Usuario estudiante = usuarioRepository.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        Entrega entrega = new Entrega();
        entrega.setEvaluacion(evaluacion);
        entrega.setEstudiante(estudiante);
        entrega.setRespuestaAlumno(respuestaTexto);

        return entregaRepository.save(entrega);
    }

    public Entrega calificarEntrega(Long idEntrega, Double nota, String feedback) {
        Entrega entrega = entregaRepository.findById(idEntrega)
                .orElseThrow(() -> new RuntimeException("Entrega no encontrada"));

        entrega.setCalificacion(nota);
        entrega.setFeedbackProfesor(feedback);

        return entregaRepository.save(entrega);
    }

    public List<Entrega> listarPorEvaluacion(Long idEvaluacion) {
        return entregaRepository.findByEvaluacionId(idEvaluacion);
    }

    public List<Entrega> listarPorEstudiante(Long idEstudiante) {
        return entregaRepository.findByEstudianteId(idEstudiante);
    }
}