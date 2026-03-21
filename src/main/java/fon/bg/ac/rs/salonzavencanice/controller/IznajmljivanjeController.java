/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.service.IznajmljivanjeService;
import fon.bg.ac.rs.salonzavencanice.util.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ana
 */
@RestController
@RequestMapping("/api/iznajmljivanje")
public class IznajmljivanjeController {

    private final IznajmljivanjeService servis;

    public IznajmljivanjeController(IznajmljivanjeService servis) {
        this.servis = servis;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<IznajmljivanjeDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(servis.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<IznajmljivanjeDto>> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.ok(servis.findById(id)));
    }

    @GetMapping("/klijent/{klijentId}")
    public ResponseEntity<ApiResponse<List<IznajmljivanjeDto>>> getByKlijent(
            @PathVariable Integer klijentId) {
        return ResponseEntity.ok(ApiResponse.ok(servis.findByKlijent(klijentId)));
    }

    @GetMapping("/aktivna")
    public ResponseEntity<ApiResponse<List<IznajmljivanjeDto>>> getAktivna() {
        return ResponseEntity.ok(ApiResponse.ok(servis.findAktivna()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<IznajmljivanjeDto>> create(
            @Valid @RequestBody IznajmljivanjeDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(servis.create(dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {
        servis.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
