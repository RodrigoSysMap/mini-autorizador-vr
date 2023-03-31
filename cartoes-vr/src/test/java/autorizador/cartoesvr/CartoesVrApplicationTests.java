package autorizador.cartoesvr;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-h2test.properties")
class CartoesVrApplicationTests {

	@Test
	@DisplayName("Carregamento de contexto")
	void contextLoads() {
	}

}
