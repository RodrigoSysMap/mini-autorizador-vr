package autorizador.cartoesvr.dto;


import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CartaoDto {

    @NotBlank(message = "Informe o número do cartão a ser cadastrado")
    @Size(min = 16, max = 16, message = "O cartão deve possuir somente 16 números")
    private String numeroCartao;

    @NotBlank(message = "Informe a senha")
    @Size(min = 6, max = 6, message = "A senha deve possuir exatamente 6 digitos.")
    private String senha;
}
