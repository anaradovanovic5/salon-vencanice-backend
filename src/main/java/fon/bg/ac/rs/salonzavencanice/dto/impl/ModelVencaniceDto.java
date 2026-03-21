/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.dto.impl;

import jakarta.validation.constraints.*;

/**
 *
 * @author Ana
 */
public class ModelVencaniceDto {

    private Integer idModel;

    @NotBlank(message = "Naziv modela je obavezan")
    private String naziv;

    private String dizajner;
    private String boja;
    private String materijal;

    public ModelVencaniceDto() {
    }

    public ModelVencaniceDto(Integer idModel, String naziv,
            String dizajner, String boja, String materijal) {
        this.idModel = idModel;
        this.naziv = naziv;
        this.dizajner = dizajner;
        this.boja = boja;
        this.materijal = materijal;
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
