package com.practicas.cursos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.practicas.cursos.dao.CursoDao;
import com.practicas.cursos.models.Profesor;
import com.practicas.cursos.utils.JWTUtil;


@RestController
public class AuthController {

    @Autowired
    private CursoDao cursoDao;

    @Autowired
    private JWTUtil jwtUtil; // inyeccion con el JWT

    //convertir el JSON a un usario automaticamente
    @RequestMapping(value = "api/login", method = RequestMethod.POST)
    public String login(@RequestBody Profesor profesor){

        Profesor profesorLogueado = cursoDao.obtenerProfesorPorCredenciales(profesor);
        if (profesorLogueado != null){ //sí el usuario logueado es distinto a null, salio todo bien :) 
 
            String tokenJWT = jwtUtil.create(String.valueOf(profesorLogueado.getId_profesor()), profesorLogueado.getNombre());
        
            return tokenJWT;
        }

        return "FAIL";
    }
}
