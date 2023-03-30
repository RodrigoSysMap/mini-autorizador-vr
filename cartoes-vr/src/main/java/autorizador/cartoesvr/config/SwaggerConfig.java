package autorizador.cartoesvr.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@EnableWebMvc
public class SwaggerConfig {
    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mini Autorizador")
                        .description("Autorizador de transações VR")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Rodrigo")
                                .url("https://teste.com")
                                .email("rodrigo.bueno@email.cm"))
                        .termsOfService("TOC")
                        .license(new License().name("Licença").url("#"))
                );
    }

}
