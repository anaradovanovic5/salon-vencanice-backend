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
@Table(name = "model_vencanice")
public class ModelVencanice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idModel;

    private String naziv;
    private String dizajner;
    private String boja;
    private String materijal;

    public ModelVencanice() {
    }

    public ModelVencanice(int idModel) {
        this.idModel = idModel;
    }

    public Integer getIdModel() {
        return idModel;
    }

    public void setIdModel(Integer idModel) {
        this.idModel = idModel;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getDizajner() {
        return dizajner;
    }

    public void setDizajner(String dizajner) {
        this.dizajner = dizajner;
    }

    public String getBoja() {
        return boja;
    }

    public void setBoja(String boja) {
        this.boja = boja;
    }

    public String getMaterijal() {
        return materijal;
    }

    public void setMaterijal(String materijal) {
        this.materijal = materijal;
    }
}
