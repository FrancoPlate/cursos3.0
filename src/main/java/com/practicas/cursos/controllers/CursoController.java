package com.practicas.cursos.controllers;

import java.util.List;

import com.practicas.cursos.repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.practicas.cursos.dao.CursoDao;
import com.practicas.cursos.models.Alumno;
import com.practicas.cursos.models.Profesor;
import com.practicas.cursos.utils.AlumnoRequest;
import com.practicas.cursos.utils.JWTUtil;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@CrossOrigin ("*") //permitir los cross
@RestController
public class CursoController {

    @Autowired //hace que la clase UsuarioDaoImp cree un objeto y la guarde en la varieble, pero si se le pone a varios este parametro va a tener la memoria compartida. tambien llamado " inyecion de dependencia"
    private CursoDao cursoDao; 

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    AlumnoRepository alumnorepo;

    //----------------------------------------- API PARA EL CURSO DE 7°5-------------------------------------------------------

    //Genera la tabla de los alumnos de 7°5
    @RequestMapping(value = "api/generarAlumnos75", method = RequestMethod.GET)
    public List<Alumno> getAlumnos75(@RequestHeader(value="Authorization") String token){

        if( !validarToken(token)){
            return null;
        }

        return cursoDao.getAlumnos75();
    }

    //peticion para elimiar a un alumno 
    @RequestMapping(value = "api/generarAlumnos75/{id_alumno}", method = RequestMethod.DELETE)
    public void eliminar75(@RequestHeader(value="Authorization") String token,@PathVariable int id_alumno){

        if( !validarToken(token)){
            return;
        }

         cursoDao.eliminar(id_alumno);
    }

    //peticion para editar a un alumno 
    @PutMapping(value = "api/generarAlumnos75/{id_alumno}")
    public ResponseEntity<Object> editar75(@RequestBody AlumnoRequest request, @PathVariable int id_alumno){

        this.cursoDao.editar(request, id_alumno);
        return ResponseEntity.ok(Boolean.TRUE);
    }


    //----------------------------------------- API PARA EL CURSO DE 7°3-------------------------------------------------------

    //Genera la tabla de los alumnos de 7°3
    @RequestMapping(value = "api/generarAlumnos73", method = RequestMethod.GET)
    public List<Alumno> getAlumnos73(@RequestHeader(value="Authorization") String token){

        if( !validarToken(token)){
            return null;
        }

        return cursoDao.getAlumnos73();
    }

    //peticion para elimiar a un alumno 
    @RequestMapping(value = "api/generarAlumnos73/{id_alumno}", method = RequestMethod.DELETE)
    public void eliminar73(@RequestHeader(value="Authorization") String token,@PathVariable int id_alumno){

        if( !validarToken(token)){
            return;
        }

        cursoDao.eliminar(id_alumno);
    }

    //peticion para editar a un alumno 
    @PutMapping(value = "api/generarAlumnos73/{id_alumno}")
    public ResponseEntity<Object> editar73(@RequestBody AlumnoRequest request, @PathVariable int id_alumno){


        this.cursoDao.editar(request, id_alumno);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    //------------------------------------ BUSCAR ALUMNO------------------------------------------------------------

    @GetMapping(value = "api/buscar/{dni}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Alumno buscarAlumnos(@PathVariable int dni){

        return cursoDao.buscar(dni);
    }

    @GetMapping(value = "api/buscarA/{id_alumno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Alumno buscarAlumno(@PathVariable int id_alumno){

        return cursoDao.buscarA(id_alumno);
    }
    
    //------------------------------------ API PROFESOR------------------------------------------------------------

    //registro del profesor 
    @RequestMapping(value = "api/profesor", method = RequestMethod.POST)
    public void registrarProfesor(@RequestBody Profesor profesor){

        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id); // encripta la contraseña, como? ni idea pero la encripta
        String hash = argon2.hash(1,1024,1, profesor.getPassword());
        profesor.setPassword(hash);

        cursoDao.registrar(profesor);

    }

    //Eliminar todos los alumnos 
    @DeleteMapping("/api/borrarTodos")
    public ResponseEntity<String> borrarTodo(@RequestHeader(value="Authorization") String token){

        if( !validarToken(token)){
            return null;
        }

        String t34 = cursoDao.borrarTodo();

        if ("OK".equalsIgnoreCase(t34)){
            return new ResponseEntity<>(t34, HttpStatus.NO_CONTENT);//se devuelve el codigo 204 cuando se elimina todo
        }else{
            return new ResponseEntity<>(t34, HttpStatus.NOT_FOUND); //se devuelve el codigo 404 cuando no funciono correctamente
        }

    }


    //Agregar a los alumnos
    @RequestMapping(value = "api/registarAlumnos", method = RequestMethod.POST)
    public void registrarAlumnos(@RequestBody Alumno alumno, @RequestHeader(value="Authorization") String token){

        
        if( !validarToken(token)){
            return;
        }

        alumno.setH_cumplidas(0);
        alumno.setH_incumplidas(200);
        alumno.setFaltas (0);


        cursoDao.registrarAlumno(alumno);
    } 

    //verifica si el token que tiene el usuario es el mismo que le dieron mediante el ID
    private boolean validarToken(String token){ 
        String profesorId = jwtUtil.getKey(token);
        return profesorId != null;
    }

}
