package autorizador.cartoesvr;

import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.enums.CartaoStatusEnum;
import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.services.CartoesService;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CartoesTeste extends  CartoesVrApplicationTests {

    @Autowired
    private CartoesService cartoesService;

    @MockBean
    private CartoesRepository cartoesRepository;

    @MockBean
    private CartoesSaldoRepository cartoesSaldoRepository;

    @Test
    @DisplayName("Criar um novo cartão")
    void testCriarUmNovoCartao(){

        CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11111");
        cartaoTeste.setSenha("123");

        assertTrue(cartoesService.criarCartao(cartaoTeste));

    }

    @Test
    @DisplayName("Buscar saldo de um cartao")
    void verificarSaldoCartao(){

      /*  CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11111");
        cartaoTeste.setSenha("123");

        Cartoes cartaoMock = null;
        when(cartoesRepository.save(any(Cartoes.class))).thenReturn(cartaoMock);

        BigDecimal testeSaldo = cartoesService.obterSaldoCartao(cartaoMock.getCartaoNumero());
        assertTrue(Long.parseLong(String.valueOf(testeSaldo)) >= 0 );*/

    }
}
