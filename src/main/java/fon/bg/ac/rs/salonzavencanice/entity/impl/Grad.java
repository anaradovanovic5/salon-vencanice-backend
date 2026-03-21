/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;

/**
 *
 * @author Ana
 */
@Entity
@Table(name = "grad")
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGrad;

    private String naziv;

    public Grad() {
    }

    public Grad(Integer idGrad) {
        this.idGrad = idGrad;
    }

    public Integer getIdGrad() {
        return idGrad;
    }

    public void setIdGrad(Integer idGrad) {
        this.idGrad = idGrad;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
