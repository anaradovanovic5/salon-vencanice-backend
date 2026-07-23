
package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.service.ModelVencaniceService;
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
@RequestMapping("/api/model-vencanice")
public class ModelVencaniceController {

    private final ModelVencaniceService servis;

    public ModelVencaniceController(ModelVencaniceService servis) {
        this.servis = servis;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ModelVencaniceDto>>> getAll() {
        return ResponseEntity.ok(ApiResponse.ok(servis.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ModelVencaniceDto>> getById(
            @PathVariable Integer id) {
        return ResponseEntity.ok(ApiResponse.ok(servis.findById(id)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ModelVencaniceDto>> create(
            @Valid @RequestBody ModelVencaniceDto dto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.ok(servis.create(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ModelVencaniceDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody ModelVencaniceDto dto) {
        return ResponseEntity.ok(ApiResponse.ok(servis.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {
        servis.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
