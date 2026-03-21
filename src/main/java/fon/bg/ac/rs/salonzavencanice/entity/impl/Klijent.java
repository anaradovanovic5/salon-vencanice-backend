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
@Table(name = "klijent")
public class Klijent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idKlijent;

    private Integer godine;

    private String telefon;
    private String ime;
    private String prezime;
    private String email;
    private String adresa;

    @ManyToOne
    @JoinColumn(name = "idGrad")
    private Grad grad;

    public Klijent() {
    }

    public Klijent(int idKlijent) {
        this.idKlijent = idKlijent;
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

    public Grad getGrad() {
        return grad;
    }

    public void setGrad(Grad grad) {
        this.grad = grad;
    }
}
