package autorizador.cartoesvr.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartaoTransacaoDto {
    private String numeroCartao;
    private String senhaCartao;
    private BigDecimal valor;
}
