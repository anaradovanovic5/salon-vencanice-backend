
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.GradDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.GradService;
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
@WebMvcTest(GradController.class)
@AutoConfigureMockMvc(addFilters = false)
public class GradControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GradService gradService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAll_vratiListuGradova() throws Exception {
        when(gradService.findAll()).thenReturn(List.of(new GradDto(1, "Beograd"), new GradDto(2, "Novi Sad")));

        mockMvc.perform(get("/api/grad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data[0].naziv").value("Beograd"))
                .andExpect(jsonPath("$.data[1].naziv").value("Novi Sad"));
    }

    @Test
    void getAll_praznaLista_vratiPrazan() throws Exception {
        when(gradService.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/grad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data").isEmpty());
    }

    @Test
    void getById_postojeciId_vratiGrad() throws Exception {
        when(gradService.findById(1)).thenReturn(new GradDto(1, "Beograd"));

        mockMvc.perform(get("/api/grad/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.idGrad").value(1))
                .andExpect(jsonPath("$.data.naziv").value("Beograd"));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(gradService.findById(99)).thenThrow(new EntityNotFoundException("Grad", 99));

        mockMvc.perform(get("/api/grad/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        GradDto ulaz = new GradDto(null, "Kragujevac");
        GradDto odgovor = new GradDto(3, "Kragujevac");
        when(gradService.create(any(GradDto.class))).thenReturn(odgovor);

        mockMvc.perform(post("/api/grad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idGrad").value(3))
                .andExpect(jsonPath("$.data.naziv").value("Kragujevac"));
    }

    @Test
    void create_prazanNaziv_vrati400() throws Exception {
        GradDto ulaz = new GradDto(null, "");

        mockMvc.perform(post("/api/grad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void create_nullNaziv_vrati400() throws Exception {
        GradDto ulaz = new GradDto(null, null);

        mockMvc.perform(post("/api/grad")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_postojeciId_vratiAzuriran() throws Exception {
        GradDto ulaz = new GradDto(null, "Nis");
        GradDto odgovor = new GradDto(1, "Nis");
        when(gradService.update(eq(1), any(GradDto.class))).thenReturn(odgovor);

        mockMvc.perform(put("/api/grad/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.naziv").value("Nis"));
    }

    @Test
    void update_nepostojeciId_vrati404() throws Exception {
        GradDto ulaz = new GradDto(null, "Nis");
        when(gradService.update(eq(99), any(GradDto.class)))
                .thenThrow(new EntityNotFoundException("Grad", 99));

        mockMvc.perform(put("/api/grad/99")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(gradService).delete(1);

        mockMvc.perform(delete("/api/grad/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }

    @Test
    void delete_nepostojeciId_vrati404() throws Exception {
        doThrow(new EntityNotFoundException("Grad", 99)).when(gradService).delete(99);

        mockMvc.perform(delete("/api/grad/99"))
                .andExpect(status().isNotFound());
    }
}