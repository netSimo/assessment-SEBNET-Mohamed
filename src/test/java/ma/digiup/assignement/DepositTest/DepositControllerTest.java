package ma.digiup.assignement.DepositTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import ma.digiup.assignement.domain.Compte;
import ma.digiup.assignement.service.DepositService;
import ma.digiup.assignement.web.DepositController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

@WebMvcTest(DepositController.class)
@AutoConfigureMockMvc

public class DepositControllerTest {

    private DepositController depositController;
    private DepositService depositService;

    @BeforeEach
    void setUp() {
        depositService = mock(DepositService.class);
        depositController = new DepositController();
        depositController.setDepositService(depositService);
    }

    @Test
    void testDepositSuccess() {
        // Arrange
        String rib = "RIB1";
        Double amount = 100.0;
        Compte compte1 = new Compte();
        compte1.setNrCompte("010000A000001000");
        compte1.setRib("RIB1");
        compte1.setSolde(BigDecimal.valueOf(200000L));


        when(depositService.deposit(rib, amount)).thenReturn(compte1);

        // Act
        ResponseEntity<Compte> response = depositController.Deposit(rib, amount);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(compte1, response.getBody());
        verify(depositService, times(1)).deposit(rib, amount);
    }

    @Test
    void testDepositException() {
        // Arrange
        String rib = "123456789";
        Double amount = 100.0;

        when(depositService.deposit(rib, amount)).thenThrow(new RuntimeException("Test exception"));

        // Act
        ResponseEntity<Compte> response = depositController.Deposit(rib, amount);

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertNull(response.getBody());
        verify(depositService, times(1)).deposit(rib, amount);
    }


}
