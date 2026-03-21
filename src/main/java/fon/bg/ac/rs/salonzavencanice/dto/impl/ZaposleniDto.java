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
public class ZaposleniDto {

    private Integer idZaposleni;

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    @NotBlank(message = "Korisnicko ime je obavezno")
    private String korisnickoIme;

    private String lozinka;

    public ZaposleniDto() {
    }

    public ZaposleniDto(Integer idZaposleni, String ime,
            String prezime, String korisnickoIme) {
        this.idZaposleni = idZaposleni;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
    }

    public Integer getIdZaposleni() {
        return idZaposleni;
    }

    public void setIdZaposleni(Integer id) {
        this.idZaposleni = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String k) {
        this.korisnickoIme = k;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
}
