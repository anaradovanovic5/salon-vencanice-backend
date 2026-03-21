/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.service.GradService;
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
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Ana
 */
@RestController
@RequestMapping("/api/grad")
public class GradController {

    private final GradService gradServis;

    public GradController(GradService gradServis) {
        this.gradServis = gradServis;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<GradDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(gradServis.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GradDto>> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.ok(gradServis.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<GradDto>> create(
            @Valid @RequestBody GradDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(gradServis.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GradDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody GradDto dto) {
        return ResponseEntity.ok(ApiResponse.ok(gradServis.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {
        gradServis.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
