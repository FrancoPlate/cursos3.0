package com.practicas.cursos.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "profesor")

public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter @Column(name= "id_profesor")
    private int id_profesor;
    
    @Getter @Setter @Column(name= "nombre")
    private String nombre;
    
    @Getter @Setter @Column(name= "password")
    private String password;
    
}


// manera en la que sprint le otorga una fecha a una nueva cuenta automaticamente
// @Temporal(TemporalType.TIMESTAMP)
//  private Date nombre;
//
//
//  @PrePersist
//  public void prePersist(){
//  this.nombre = new Date();
//}