/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.dto.impl;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

/**
 *
 * @author Ana
 */
public class IznajmljivanjeDto {

    private Integer idIznajmljivanje;

    @NotNull(message = "Datum uzimanja je obavezan")
    private LocalDate datumUzimanja;

    @NotNull(message = "Datum vracanja je obavezan")
    private LocalDate datumVracanja;

    @NotNull(message = "Vencanica je obavezna")
    private Integer vencanicaId;

    @NotNull(message = "Klijent je obavezan")
    private Integer klijentId;

    @NotNull(message = "Zaposleni je obavezan")
    private Integer zaposleniId;

    public IznajmljivanjeDto() {
    }

    public IznajmljivanjeDto(Integer idIznajmljivanje, LocalDate datumUzimanja,
            LocalDate datumVracanja, Integer vencanicaId,
            Integer klijentId, Integer zaposleniId) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.datumUzimanja = datumUzimanja;
        this.datumVracanja = datumVracanja;
        this.vencanicaId = vencanicaId;
        this.klijentId = klijentId;
        this.zaposleniId = zaposleniId;
    }

    public Integer getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(Integer id) {
        this.idIznajmljivanje = id;
    }

    public LocalDate getDatumUzimanja() {
        return datumUzimanja;
    }

    public void setDatumUzimanja(LocalDate d) {
        this.datumUzimanja = d;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate d) {
        this.datumVracanja = d;
    }

    public Integer getVencanicaId() {
        return vencanicaId;
    }

    public void setVencanicaId(Integer id) {
        this.vencanicaId = id;
    }

    public Integer getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(Integer id) {
        this.klijentId = id;
    }

    public Integer getZaposleniId() {
        return zaposleniId;
    }

    public void setZaposleniId(Integer id) {
        this.zaposleniId = id;
    }
}
