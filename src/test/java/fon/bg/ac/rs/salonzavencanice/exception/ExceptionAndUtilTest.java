
package fon.bg.ac.rs.salonzavencanice.exception;

import fon.bg.ac.rs.salonzavencanice.util.ApiResponse;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Ana
 */
public class ExceptionAndUtilTest {

    // EntityNotFoundException
    @Test
    void entityNotFoundException_porukaSadrziEntitetIId() {
        EntityNotFoundException ex = new EntityNotFoundException("Grad", 5);
        assertThat(ex.getMessage()).contains("Grad");
        assertThat(ex.getMessage()).contains("5");
    }

    @Test
    void entityNotFoundException_porukaSadrziStringId() {
        EntityNotFoundException ex = new EntityNotFoundException("Vencanica", "SB001");
        assertThat(ex.getMessage()).contains("Vencanica");
        assertThat(ex.getMessage()).contains("SB001");
    }

    @Test
    void entityNotFoundException_jeRuntimeException() {
        EntityNotFoundException ex = new EntityNotFoundException("Klijent", 10);
        assertThat(ex).isInstanceOf(RuntimeException.class);
    }

    // ApiResponse
    @Test
    void apiResponse_ok_successTrue() {
        ApiResponse<String> response = ApiResponse.ok("test");
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo("test");
        assertThat(response.getMessage()).isNull();
    }

    @Test
    void apiResponse_ok_saNull() {
        ApiResponse<Void> response = ApiResponse.ok(null);
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isNull();
    }

    @Test
    void apiResponse_error_successFalse() {
        ApiResponse<Object> response = ApiResponse.error("Greška!");
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEqualTo("Greška!");
        assertThat(response.getData()).isNull();
    }

    @Test
    void apiResponse_ok_saIntegerom() {
        ApiResponse<Integer> response = ApiResponse.ok(42);
        assertThat(response.isSuccess()).isTrue();
        assertThat(response.getData()).isEqualTo(42);
    }

    @Test
    void apiResponse_error_saPraznomPorukom() {
        ApiResponse<Object> response = ApiResponse.error("");
        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getMessage()).isEmpty();
    }
}

