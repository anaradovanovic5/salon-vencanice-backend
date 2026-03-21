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
@Table(name = "vencanica")
public class Vencanica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idVencanica;

    private int status;
    private int godinaProizvodnje;
    private String serijskiBroj;
    private String napomene;
    private String velicina;

    @ManyToOne
    @JoinColumn(name = "idModel")
    private ModelVencanice modelVencanice;

    public Vencanica() {
    }

    public Vencanica(int idVencanica) {
        this.idVencanica = idVencanica;
    }

    public Integer getIdVencanica() {
        return idVencanica;
    }

    public void setIdVencanica(Integer idVencanica) {
        this.idVencanica = idVencanica;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }

    public String getNapomene() {
        return napomene;
    }

    public void setNapomene(String napomene) {
        this.napomene = napomene;
    }

    public String getVelicina() {
        return velicina;
    }

    public void setVelicina(String velicina) {
        this.velicina = velicina;
    }

    public ModelVencanice getModelVencanice() {
        return modelVencanice;
    }

    public void setModelVencanice(ModelVencanice modelVencanice) {
        this.modelVencanice = modelVencanice;
    }
}
