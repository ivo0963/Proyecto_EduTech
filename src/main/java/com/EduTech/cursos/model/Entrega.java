package com.EduTech.cursos.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "entregas")
@NoArgsConstructor
@AllArgsConstructor
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String respuestaAlumno; // Puede ser un texto o un link a un PDF

    private Double calificacion;    // La nota (puede ser nula si aun no corrigen)
    private String feedbackProfesor;// Comentario del profe al corregir

    private LocalDateTime fechaEntrega = LocalDateTime.now();

    // Relaciones
    @ManyToOne
    @JoinColumn(name = "id_evaluacion", nullable = false)
    private Evaluacion evaluacion;

    @ManyToOne
    @JoinColumn(name = "id_estudiante", nullable = false)
    private Usuario estudiante;
}