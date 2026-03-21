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
public class GradDto {

    private Integer idGrad;

    @NotBlank(message = "Naziv grada je obavezan")
    private String naziv;

    public GradDto() {
    }

    public GradDto(Integer idGrad, String naziv) {
        this.idGrad = idGrad;
        this.naziv = naziv;
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
