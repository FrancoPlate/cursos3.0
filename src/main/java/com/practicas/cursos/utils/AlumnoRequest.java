package com.practicas.cursos.utils;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.io.Serializable;


@Data
public class AlumnoRequest  implements Serializable{

    @JsonProperty("h_cumplidas")
    private int h_cumplidas;
    
    @JsonProperty("h_incumplidas")
    private int h_incumplidas;
    
    @JsonProperty("faltas")
    private int faltas;
    
}
