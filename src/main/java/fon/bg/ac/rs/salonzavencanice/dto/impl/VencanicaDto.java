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
public class VencanicaDto {

    private Integer idVencanica;

    private int status;
    private int godinaProizvodnje;

    @NotBlank(message = "Serijski broj je obavezan")
    private String serijskiBroj;

    private String napomene;
    private String velicina;

    @NotNull(message = "Model vencanice je obavezan")
    private Integer modelId;

    public VencanicaDto() {
    }

    public VencanicaDto(Integer idVencanica, int status, int godinaProizvodnje,
            String serijskiBroj, String napomene,
            String velicina, Integer modelId) {
        this.idVencanica = idVencanica;
        this.status = status;
        this.godinaProizvodnje = godinaProizvodnje;
        this.serijskiBroj = serijskiBroj;
        this.napomene = napomene;
        this.velicina = velicina;
        this.modelId = modelId;
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

    public void setGodinaProizvodnje(int g) {
        this.godinaProizvodnje = g;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(String s) {
        this.serijskiBroj = s;
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

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }
}
