/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.entity.impl;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 *
 * @author Ana
 */

@Entity
@Table(name = "iznajmljivanje")
public class Iznajmljivanje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIznajmljivanje;

    private LocalDate datumUzimanja;
    private LocalDate datumVracanja;

    @ManyToOne
    @JoinColumn(name = "idVencanica")
    private Vencanica vencanica;

    @ManyToOne
    @JoinColumn(name = "idKlijent")
    private Klijent klijent;

    @ManyToOne
    @JoinColumn(name = "idZaposleni")
    private Zaposleni zaposleni;

    public Iznajmljivanje() {
    }

    public Integer getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(Integer idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public LocalDate getDatumUzimanja() {
        return datumUzimanja;
    }

    public void setDatumUzimanja(LocalDate datumUzimanja) {
        this.datumUzimanja = datumUzimanja;
    }

    public LocalDate getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(LocalDate datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public Vencanica getVencanica() {
        return vencanica;
    }

    public void setVencanica(Vencanica vencanica) {
        this.vencanica = vencanica;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }
}
