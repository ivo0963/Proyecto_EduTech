package com.EduTech.cursos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "entregas")
@Data // Lombok genera getters y setters automáticamente
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String respuestaAlumno;

    private Double calificacion;

    private String feedbackProfesor;

    private LocalDateTime fechaEntrega;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Usuario estudiante;

    @ManyToOne
    @JoinColumn(name = "id_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    @PrePersist
    public void prePersist() {
        this.fechaEntrega = LocalDateTime.now();
    }
}