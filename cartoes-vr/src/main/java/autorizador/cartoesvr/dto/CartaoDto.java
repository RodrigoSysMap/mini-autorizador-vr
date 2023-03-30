package autorizador.cartoesvr.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoDto {
    private String numeroCartao;
    private String senha;
}
