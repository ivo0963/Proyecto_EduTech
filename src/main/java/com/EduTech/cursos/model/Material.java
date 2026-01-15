package com.EduTech.cursos.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "materiales")
@NoArgsConstructor
@AllArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo; // Ej: "Clase 1: Introducción a Java"

    private String descripcion; // Ej: "Video explicativo sobre variables"

    @Column(nullable = false)
    private String urlRecurso; // Ej: "https://youtube.com/..."

    // CONEXIÓN CLAVE: Muchos materiales pertenecen a UN Curso
    @ManyToOne
    @JoinColumn(name = "id_curso", nullable = false)
    private Curso curso;
}