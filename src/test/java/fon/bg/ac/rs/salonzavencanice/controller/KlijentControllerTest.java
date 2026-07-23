
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.KlijentDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.KlijentService;
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
@WebMvcTest(KlijentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class KlijentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private KlijentService klijentService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAll_vratiListu() throws Exception {
        when(klijentService.findAll()).thenReturn(List.of(
                new KlijentDto(1, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", 1)
        ));

        mockMvc.perform(get("/api/klijent"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].ime").value("Ana"));
    }

    @Test
    void getById_postojeciId_vratiKlijenta() throws Exception {
        when(klijentService.findById(1)).thenReturn(
                new KlijentDto(1, 25, "064", "Ana", "Anic", "a@a.com", "ul 1", 1)
        );

        mockMvc.perform(get("/api/klijent/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.ime").value("Ana"));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(klijentService.findById(99)).thenThrow(new EntityNotFoundException("Klijent", 99));

        mockMvc.perform(get("/api/klijent/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        KlijentDto ulaz = new KlijentDto(null, 25, "0641234567", "Jelena", "Jovic", "j@j.com", "ul 1", 1);
        KlijentDto odgovor = new KlijentDto(5, 25, "0641234567", "Jelena", "Jovic", "j@j.com", "ul 1", 1);
        when(klijentService.create(any())).thenReturn(odgovor);

        mockMvc.perform(post("/api/klijent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idKlijent").value(5));
    }

    @Test
    void create_premladKlijent_vrati400() throws Exception {
        // godine < 18
        KlijentDto ulaz = new KlijentDto(null, 15, "064", "Mala", "Malovic", null, null, 1);

        mockMvc.perform(post("/api/klijent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_prazanTelefon_vrati400() throws Exception {
        KlijentDto ulaz = new KlijentDto(null, 25, "", "Ana", "Anic", null, null, null);

        mockMvc.perform(post("/api/klijent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_validanDto_vratiAzuriran() throws Exception {
        KlijentDto ulaz = new KlijentDto(null, 30, "0641111111", "Milica", "Milicic", null, null, 1);
        KlijentDto odgovor = new KlijentDto(1, 30, "0641111111", "Milica", "Milicic", null, null, 1);
        when(klijentService.update(eq(1), any())).thenReturn(odgovor);

        mockMvc.perform(put("/api/klijent/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.ime").value("Milica"));
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(klijentService).delete(1);

        mockMvc.perform(delete("/api/klijent/1"))
                .andExpect(status().isOk());
    }
}