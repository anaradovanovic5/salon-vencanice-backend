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
public class KlijentDto {

    private Integer idKlijent;

    @Min(value = 18, message = "Klijent mora imati najmanje 18 godina")
    private int godine;

    @NotBlank(message = "Telefon je obavezan")
    private String telefon;

    @NotBlank(message = "Ime je obavezno")
    private String ime;

    @NotBlank(message = "Prezime je obavezno")
    private String prezime;

    private String email;
    private String adresa;

    private Integer gradId;

    public KlijentDto() {
    }

    public KlijentDto(Integer idKlijent, int godine, String telefon,
            String ime, String prezime, String email,
            String adresa, Integer gradId) {
        this.idKlijent = idKlijent;
        this.godine = godine;
        this.telefon = telefon;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.adresa = adresa;
        this.gradId = gradId;
    }

    public Integer getIdKlijent() {
        return idKlijent;
    }

    public void setIdKlijent(Integer idKlijent) {
        this.idKlijent = idKlijent;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Integer getGradId() {
        return gradId;
    }

    public void setGradId(Integer gradId) {
        this.gradId = gradId;
    }
}
