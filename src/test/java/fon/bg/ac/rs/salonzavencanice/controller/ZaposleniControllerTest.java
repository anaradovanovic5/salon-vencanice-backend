
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ZaposleniDto;
import fon.bg.ac.rs.salonzavencanice.entity.impl.Uloga;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.ZaposleniService;
import fon.bg.ac.rs.salonzavencanice.security.JwtUtil;
import fon.bg.ac.rs.salonzavencanice.security.CustomUserDetailsService;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Ana
 */
@WebMvcTest(ZaposleniController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ZaposleniControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ZaposleniService zaposleniService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAll_vratiListu() throws Exception {
        when(zaposleniService.findAll()).thenReturn(List.of(
                new ZaposleniDto(1, "Ana", "Anic", "aanic", null, Uloga.ZAPOSLENI),
                new ZaposleniDto(2, "Marko", "Markovic", "mmarkovic", null, Uloga.ZAPOSLENI)
        ));

        mockMvc.perform(get("/api/zaposleni"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].ime").value("Ana"))
                .andExpect(jsonPath("$.data[1].ime").value("Marko"));
    }

    @Test
    void getById_postojeciId_vratiZaposlenog() throws Exception {
        when(zaposleniService.findById(1)).thenReturn(new ZaposleniDto(1, "Ana", "Anic", "aanic", null, Uloga.ZAPOSLENI));

        mockMvc.perform(get("/api/zaposleni/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.ime").value("Ana"));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(zaposleniService.findById(99)).thenThrow(new EntityNotFoundException("Zaposleni", 99));

        mockMvc.perform(get("/api/zaposleni/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        ZaposleniDto ulaz = new ZaposleniDto(null, "Petar", "Petrovic", "ppetrovic", "tajna123", Uloga.ZAPOSLENI);
        ZaposleniDto odgovor = new ZaposleniDto(3, "Petar", "Petrovic", "ppetrovic", null, Uloga.ZAPOSLENI);
        when(zaposleniService.create(any())).thenReturn(odgovor);

        mockMvc.perform(post("/api/zaposleni")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idZaposleni").value(3));
    }

    @Test
    void create_praznoIme_vrati400() throws Exception {
        ZaposleniDto ulaz = new ZaposleniDto(null, "", "Petrovic", "ppetrovic", "pass", Uloga.ZAPOSLENI);

        mockMvc.perform(post("/api/zaposleni")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_praznoKorisnickoIme_vrati400() throws Exception {
        ZaposleniDto ulaz = new ZaposleniDto(null, "Ana", "Anic", "", "pass", Uloga.ZAPOSLENI);

        mockMvc.perform(post("/api/zaposleni")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_validanDto_vratiAzuriran() throws Exception {
        ZaposleniDto ulaz = new ZaposleniDto(null, "Petar", "Petrovic", "ppetrovic", "tajna123", Uloga.ZAPOSLENI);
        ZaposleniDto odgovor = new ZaposleniDto(1, "Petar", "Petrovic", "ppetrovic", null, Uloga.ZAPOSLENI);
        when(zaposleniService.update(eq(1), any())).thenReturn(odgovor);

        mockMvc.perform(put("/api/zaposleni/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.idZaposleni").value(1));
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(zaposleniService).delete(1);

        mockMvc.perform(delete("/api/zaposleni/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void delete_nepostojeciId_vrati404() throws Exception {
        doThrow(new EntityNotFoundException("Zaposleni", 99)).when(zaposleniService).delete(99);

        mockMvc.perform(delete("/api/zaposleni/99"))
                .andExpect(status().isNotFound());
    }
}