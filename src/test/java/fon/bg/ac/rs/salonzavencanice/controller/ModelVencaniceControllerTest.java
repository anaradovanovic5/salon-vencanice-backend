
package fon.bg.ac.rs.salonzavencanice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import fon.bg.ac.rs.salonzavencanice.dto.impl.ModelVencaniceDto;
import fon.bg.ac.rs.salonzavencanice.exception.EntityNotFoundException;
import fon.bg.ac.rs.salonzavencanice.service.ModelVencaniceService;
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
@WebMvcTest(ModelVencaniceController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ModelVencaniceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ModelVencaniceService modelVencaniceService;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private CustomUserDetailsService customUserDetailsService;

    @Test
    void getAll_vratiListu() throws Exception {
        when(modelVencaniceService.findAll()).thenReturn(List.of(
                new ModelVencaniceDto(1, "Bella", "Vera Wang", "bela", "svila")
        ));

        mockMvc.perform(get("/api/model-vencanice"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data[0].naziv").value("Bella"));
    }

    @Test
    void getById_postojeciId_vratiModel() throws Exception {
        when(modelVencaniceService.findById(1)).thenReturn(
                new ModelVencaniceDto(1, "Bella", "Vera Wang", "bela", "svila")
        );

        mockMvc.perform(get("/api/model-vencanice/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.naziv").value("Bella"));
    }

    @Test
    void getById_nepostojeciId_vrati404() throws Exception {
        when(modelVencaniceService.findById(99)).thenThrow(new EntityNotFoundException("ModelVencanice", 99));

        mockMvc.perform(get("/api/model-vencanice/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_validanDto_vrati201() throws Exception {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(null, "Grace", "Chanel", "kremasta", "taft");
        ModelVencaniceDto odgovor = new ModelVencaniceDto(2, "Grace", "Chanel", "kremasta", "taft");
        when(modelVencaniceService.create(any())).thenReturn(odgovor);

        mockMvc.perform(post("/api/model-vencanice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.idModel").value(2));
    }

    @Test
    void create_prazanNaziv_vrati400() throws Exception {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(null, "", "Chanel", "bela", "svila");

        mockMvc.perform(post("/api/model-vencanice")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void update_validanDto_vratiAzuriran() throws Exception {
        ModelVencaniceDto ulaz = new ModelVencaniceDto(null, "Updated", "Dizajner", "crna", "cipka");
        ModelVencaniceDto odgovor = new ModelVencaniceDto(1, "Updated", "Dizajner", "crna", "cipka");
        when(modelVencaniceService.update(eq(1), any())).thenReturn(odgovor);

        mockMvc.perform(put("/api/model-vencanice/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ulaz)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.naziv").value("Updated"));
    }

    @Test
    void delete_postojeciId_vratiOk() throws Exception {
        doNothing().when(modelVencaniceService).delete(1);

        mockMvc.perform(delete("/api/model-vencanice/1"))
                .andExpect(status().isOk());
    }

    @Test
    void delete_nepostojeciId_vrati404() throws Exception {
        doThrow(new EntityNotFoundException("ModelVencanice", 99)).when(modelVencaniceService).delete(99);

        mockMvc.perform(delete("/api/model-vencanice/99"))
                .andExpect(status().isNotFound());
    }
}