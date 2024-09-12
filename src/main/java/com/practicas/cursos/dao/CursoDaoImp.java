package com.practicas.cursos.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.practicas.cursos.models.Alumno;
import com.practicas.cursos.models.Profesor;
import com.practicas.cursos.repository.AlumnoRepository;
import com.practicas.cursos.utils.AlumnoRequest;


import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class CursoDaoImp implements CursoDao{
    

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    AlumnoRepository alumnoRepository;


    
    @Override
    public Alumno buscar(int dni){

        // Una variable Optional contempla que pueda ser nula
        // En este caso, la usamos porque quizás no exista usuario que coincida con el DNI
        Optional<Alumno> us = alumnoRepository.findByDni(dni);
        // Optional.get() devuelve el valor si existe, y devuelve una excepción si no existe
        return us.get();
            
    }

    @Override
    public Alumno buscarA(int id_alumno){

        // Una variable Optional contempla que pueda ser nula
        // En este caso, la usamos porque quizás no exista usuario que coincida con el DNI
        Optional<Alumno> us = alumnoRepository.findById(id_alumno);
        // Optional.get() devuelve el valor si existe, y devuelve una excepción si no existe
        return us.get();

    }


    //-----------------------------Alumno 7°5-------------------------------------------------------------------

    @Override
    public List<Alumno> getAlumnos75(){
            
        String query = "FROM Alumno WHERE `curso_id` = '2'";
        return entityManager.createQuery(query, Alumno.class).getResultList();

    }   
    //elimina a un alumno 
    @Override
    public void eliminar(int id_alumno) {
        
        Alumno alumno = entityManager.find(Alumno.class, id_alumno);
        entityManager.remove(alumno);
    }

    //Edita a un alumno
    @Override
    public void editar(AlumnoRequest request, int id_alumno) {

        Optional<Alumno> alumnos = this.alumnoRepository.findById(id_alumno);

        Alumno alumno = alumnos.get();
        alumno.setH_cumplidas(request.getH_cumplidas());
        alumno.setH_incumplidas(request.getH_incumplidas());
        alumno.setFaltas(request.getFaltas());
        
        this.alumnoRepository.save(alumno);

    }


    //--------------------------Alumno 7°3----------------------------------------------------------------------
    
    @Override
    public List<Alumno> getAlumnos73(){
            
        String query = "FROM Alumno WHERE `curso_id` = '1'";
        return entityManager.createQuery(query, Alumno.class).getResultList();

    }


    //-------------------------------Profesor-----------------------------------------------------------------


    @Override
    public void registrar(Profesor profesor) {
        entityManager.merge(profesor);
        
    }

    @Override
    public void registrarAlumno(Alumno alumno) {
        entityManager.merge(alumno);
        
    }

    @Override
    public String borrarTodo() {
        
        this.alumnoRepository.deleteAll();
        return "OK";
    }


    @Override // consulta a la DB para saber si el usuario existe en la DB, sí existe verificar las contraseñas y devolver un token
    public Profesor obtenerProfesorPorCredenciales(Profesor profesor) {
        String query = "FROM Profesor WHERE nombre = :nombre";

        List<Profesor> lista = entityManager.createQuery(query, Profesor.class)   //si encunetra a un usuario con las mismas credenciales me devolvera una lista, sino no me va a devolver nada
        .setParameter("nombre", profesor.getNombre())
        .getResultList();

        if(lista.isEmpty()){  //Una solución simple para verificar si una lista está vacía en Java.Este metodo devuelve verdadero si la lista no contiene elementos.
            return null;
        }

        String passwordHashed = lista.get(0).getPassword();

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if  (argon2.verify(passwordHashed, profesor.getPassword())){     //si encuentra a un usuario con la misma contraseña, devolvera todos los datos del usuario
            return lista.get(0);
        }

        return null;
    }

    

}



//        if ( !request.getPassword().isEmpty()){ por si se modifica la contraseña
//
//           Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); // encripta la contraseña, como? ni idea pero la encripta
//           String hash = argon2.hash(1,1024,1, profesor.getPassword());
//           profesor.setPassword(hash);
//        }
