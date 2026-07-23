
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.IznajmljivanjeDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.IznajmljivanjeService;
import fon.bg.ac.rs.salonzavencanice.security.JwtUtil;
import fon.bg.ac.rs.salonzavencanice.security.CustomUserDetailsService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ana
 */
@WebMvcTest(IznajmljivanjeController.class)
@AutoConfigureMockMvc(addFilters = false)
public class IznajmljivanjeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IznajmljivanjeService iznajmljivanjeService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    private IznajmljivanjeDto napraviDto(Integer id) {
        return new IznajmljivanjeDto(id,
                LocalDate.of(2024, 5, 1),
                LocalDate.of(2024, 5, 10),
                1, 2, 3);
    }

    @Test
    void getAll_vratiListu() throws Exception {
        when(iznajmljivanjeService.findAll()).thenReturn(List.of(napraviDto(1), napraviDto(2)));

        mockMvc.perform(get("/api/iznajmljivanje"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void getById_postojeciId_vratiIznajmljivanje() throws Exception {
        when(iznajmljivanjeService.findById(1)).thenReturn(napraviDto(1));

        mockMvc.perform(get("/api/iznajmljivanje/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.vencanicaId").value(1));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(iznajmljivanjeService.findById(99)).thenThrow(new EntityNotFoundException("Iznajmljivanje", 99));

        mockMvc.perform(get("/api/iznajmljivanje/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getByKlijent_vratiIznajmljivanjaZaKlijenta() throws Exception {
        when(iznajmljivanjeService.findByKlijent(2)).thenReturn(List.of(napraviDto(1)));

        mockMvc.perform(get("/api/iznajmljivanje/klijent/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].klijentId").value(2));
    }

    @Test
    void getAktivna_vratiAktivnaIznajmljivanja() throws Exception {
        when(iznajmljivanjeService.findAktivna()).thenReturn(List.of(napraviDto(1)));

        mockMvc.perform(get("/api/iznajmljivanje/aktivna"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(1));
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        IznajmljivanjeDto ulaz = napraviDto(null);
        IznajmljivanjeDto odgovor = napraviDto(5);
        when(iznajmljivanjeService.create(any())).thenReturn(odgovor);

        mockMvc.perform(post("/api/iznajmljivanje")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idIznajmljivanje").value(5));
    }

    @Test
    void create_nullDatumUzimanja_vrati400() throws Exception {
        IznajmljivanjeDto ulaz = new IznajmljivanjeDto(null, null,
                LocalDate.of(2024, 5, 10), 1, 2, 3);

        mockMvc.perform(post("/api/iznajmljivanje")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_vencanicaNijeSlobodna_vrati409() throws Exception {
        IznajmljivanjeDto ulaz = napraviDto(null);
        when(iznajmljivanjeService.create(any()))
                .thenThrow(new IllegalStateException("Vencanica sa ID 1 nije slobodna!"));

        mockMvc.perform(post("/api/iznajmljivanje")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(iznajmljivanjeService).delete(1);

        mockMvc.perform(delete("/api/iznajmljivanje/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_nepostojeciId_vrati404() throws Exception {
        doThrow(new EntityNotFoundException("Iznajmljivanje", 99)).when(iznajmljivanjeService).delete(99);

        mockMvc.perform(delete("/api/iznajmljivanje/99"))
                .andExpect(status().isNotFound());
    }
}