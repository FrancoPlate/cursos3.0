package com.practicas.cursos.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.practicas.cursos.models.Alumno;



@Repository
public interface AlumnoRepository extends CrudRepository<Alumno, Integer>{
    

   Optional<Alumno> findByDni(int dni);
   Optional<Alumno> findById(int id_alumno);
   
}
