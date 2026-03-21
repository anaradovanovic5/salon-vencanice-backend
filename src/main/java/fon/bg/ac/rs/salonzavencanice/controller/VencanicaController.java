/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.service.VencanicaService;
import fon.bg.ac.rs.salonzavencanice.util.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ana
 */
@RestController
@RequestMapping("/api/vencanica")
public class VencanicaController {

    private final VencanicaService servis;

    public VencanicaController(VencanicaService servis) {
        this.servis = servis;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<VencanicaDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(servis.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<VencanicaDto>> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.ok(servis.findById(id)));
    }

    @GetMapping("/slobodne")
    public ResponseEntity<ApiResponse<List<VencanicaDto>>> getSlobodne() {
        return ResponseEntity.ok(ApiResponse.ok(servis.findSlobodne()));
    }

    @GetMapping("/velicina")
    public ResponseEntity<ApiResponse<List<VencanicaDto>>> getByVelicina(
            @RequestParam String velicina) {
        return ResponseEntity.ok(ApiResponse.ok(servis.findByVelicina(velicina)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<VencanicaDto>> create(
            @Valid @RequestBody VencanicaDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(servis.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<VencanicaDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody VencanicaDto dto) {
        return ResponseEntity.ok(ApiResponse.ok(servis.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {
        servis.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
