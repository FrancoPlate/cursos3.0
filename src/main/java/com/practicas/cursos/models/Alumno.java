package com.practicas.cursos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "alumnos")
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name= "id_alumno")
    private int id_alumno;
    
    @Getter @Setter @Column(name= "nombre")
    private String nombre;
    
    @Getter @Setter @Column(name= "apellido")
    private String apellido;

    @Getter @Setter @Column(name= "dni")
    private int dni;

    @Getter @Setter @Column(name= "h_cumplidas")
    private int h_cumplidas;

    @Getter @Setter @Column(name= "h_incumplidas")
    private int h_incumplidas;

    @Getter @Setter @Column(name= "faltas")
    private int faltas;

    @Getter @Setter @Column(name= "curso_id")
    private String curso_id;

}
