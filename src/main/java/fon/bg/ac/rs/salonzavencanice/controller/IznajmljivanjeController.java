
package fon.bg.ac.rs.salonzavencanice.controller;

import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.service.IznajmljivanjeService;
import fon.bg.ac.rs.salonzavencanice.util.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<IznajmljivanjeDto>> update(
            @PathVariable Integer id,
            @Valid @RequestBody IznajmljivanjeDto dto) {
        return ResponseEntity.ok(ApiResponse.ok(servis.update(id, dto)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Integer id) {
        servis.delete(id);
        return ResponseEntity.ok(ApiResponse.ok(null));
    }
}
