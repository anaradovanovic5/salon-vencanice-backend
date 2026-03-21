/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.exception;

/**
 *
 * @author Ana
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String entitet, Object id) {
        super(entitet + " sa ID " + id + " nije pronađen.");
    }
}
