package autorizador.cartoesvr.dto;


import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class CartaoTransacaoDto {

    @NotBlank(message = "Informe o número do cartão para a transacao")
    @Size(min = 16, max = 16, message = "O cartão deve possuir somente 16 números")
    private String numeroCartao;

    @NotBlank(message = "Informe a senha")
    @Size(min = 6, max = 6, message = "A senha deve possuir exatamente 6 digitos.")
    private String senhaCartao;

    @DecimalMin(value = "0.00", inclusive = false, message = "O valor de transação deve ser maior ou igual a 0.01")
    private BigDecimal valor;
}
