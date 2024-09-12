package com.practicas.cursos.dao;

import java.util.List;

import com.practicas.cursos.models.Alumno;
import com.practicas.cursos.models.Profesor;
import com.practicas.cursos.utils.AlumnoRequest;

public interface CursoDao {

    //-----------------Alumnos----------------------------------------
    
    List<Alumno> getAlumnos75(); // genera la tabla de alumnos

    List<Alumno> getAlumnos73(); // genera la tabla de alumnos

    Alumno buscar(int dni); //busca a un alumno por su DNI

    Alumno buscarA(int id_alumno); //busca a un alumno por su ID

    void editar(AlumnoRequest request, int id_alumno); //Edita a un alumno

    void eliminar(int id_alumno); //ELimina a un alumno

    void registrarAlumno(Alumno alumno); // guarda a un alumno


    //------------------PROFESOR-----------------------------------
    void registrar(Profesor profesor); // registra al profesor

    Profesor obtenerProfesorPorCredenciales(Profesor profesor); // busca si el profesor esta en la DB

    String borrarTodo();

}
