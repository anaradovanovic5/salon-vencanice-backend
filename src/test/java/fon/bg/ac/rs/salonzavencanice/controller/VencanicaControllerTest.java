
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.VencanicaDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.VencanicaService;
import fon.bg.ac.rs.salonzavencanice.security.JwtUtil;
import fon.bg.ac.rs.salonzavencanice.security.CustomUserDetailsService;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ana
 */
@WebMvcTest(VencanicaController.class)
@AutoConfigureMockMvc(addFilters = false)
public class VencanicaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private VencanicaService vencanicaService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAll_vratiListu() throws Exception {
        when(vencanicaService.findAll()).thenReturn(List.of(
                new VencanicaDto(1, 0, 2020, "SB001", "", "38", 1)
        ));

        mockMvc.perform(get("/api/vencanica"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].serijskiBroj").value("SB001"));
    }

    @Test
    void getById_postojeciId_vratiVencanicu() throws Exception {
        when(vencanicaService.findById(1)).thenReturn(new VencanicaDto(1, 0, 2020, "SB001", "", "38", 1));

        mockMvc.perform(get("/api/vencanica/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.serijskiBroj").value("SB001"));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(vencanicaService.findById(99)).thenThrow(new EntityNotFoundException("Vencanica", 99));

        mockMvc.perform(get("/api/vencanica/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void getSlobodne_vratiSlobodneVencanice() throws Exception {
        when(vencanicaService.findSlobodne()).thenReturn(List.of(
                new VencanicaDto(1, 0, 2020, "SB001", "", "38", 1),
                new VencanicaDto(2, 0, 2021, "SB002", "", "40", 1)
        ));

        mockMvc.perform(get("/api/vencanica/slobodne"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.length()").value(2));
    }

    @Test
    void getByVelicina_vratiVencanicePoVelicini() throws Exception {
        when(vencanicaService.findByVelicina("38")).thenReturn(List.of(
                new VencanicaDto(1, 0, 2020, "SB001", "", "38", 1)
        ));

        mockMvc.perform(get("/api/vencanica/velicina").param("velicina", "38"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].velicina").value("38"));
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        VencanicaDto ulaz = new VencanicaDto(null, 0, 2022, "SB999", "nova", "42", 1);
        VencanicaDto odgovor = new VencanicaDto(5, 0, 2022, "SB999", "nova", "42", 1);
        when(vencanicaService.create(any())).thenReturn(odgovor);

        mockMvc.perform(post("/api/vencanica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idVencanica").value(5));
    }

    @Test
    void create_prazanSerijskiBroj_vrati400() throws Exception {
        VencanicaDto ulaz = new VencanicaDto(null, 0, 2022, "", "nova", "42", 1);

        mockMvc.perform(post("/api/vencanica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_nullModelId_vrati400() throws Exception {
        VencanicaDto ulaz = new VencanicaDto(null, 0, 2022, "SB999", "nova", "42", null);

        mockMvc.perform(post("/api/vencanica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_validanDto_vratiAzuriran() throws Exception {
        VencanicaDto ulaz = new VencanicaDto(null, 1, 2022, "SB001", "izdata", "38", 1);
        VencanicaDto odgovor = new VencanicaDto(1, 1, 2022, "SB001", "izdata", "38", 1);
        when(vencanicaService.update(eq(1), any())).thenReturn(odgovor);

        mockMvc.perform(put("/api/vencanica/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.status").value(1));
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(vencanicaService).delete(1);

        mockMvc.perform(delete("/api/vencanica/1"))
                .andExpect(status().isOk());
    }
}