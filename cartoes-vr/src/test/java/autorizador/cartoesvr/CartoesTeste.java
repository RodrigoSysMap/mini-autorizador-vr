package autorizador.cartoesvr;

import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.dto.CartaoTransacaoDto;
import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import autorizador.cartoesvr.enums.CartaoStatusEnum;
import autorizador.cartoesvr.enums.ParametroGenericoEnum;
import autorizador.cartoesvr.enums.TransacaoEnum;
import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import autorizador.cartoesvr.services.CartoesService;

import autorizador.cartoesvr.services.TransacoesService;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@FixMethodOrder(MethodSorters.DEFAULT)
public class CartoesTeste extends  CartoesVrApplicationTests {

    @Mock
    CartoesRepository cartoesRepository;

    @Mock
    CartoesSaldoRepository cartoesSaldoRepository;

    @Mock
    CartoesTransacoesRepository cartoesTransacoesRepository;

    @Autowired
    private CartoesService cartoesService;

    @Autowired
    private TransacoesService transacoesService;

    @Test
    @DisplayName("Criar um novo cartão")
    void testCriarUmNovoCartao1(){

        CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11111");
        cartaoTeste.setSenha("123");

        boolean cardCreated = cartoesService.criarCartao(cartaoTeste);
        assertTrue(cardCreated);

    }

    @Test
    @DisplayName("Buscar saldo de um cartão recém cadastrado")
    void testVerificarSaldoCartaoPositivo2(){

        CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11112");
        cartaoTeste.setSenha("123");

        boolean cardCreated = cartoesService.criarCartao(cartaoTeste);

        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero("11112");
        assertTrue(cartaoInfo.get().getSaldo().getVlrSaldo().compareTo(BigDecimal.ZERO) >=0);

    }
    @Test
    @DisplayName("Saldo inicial do cartão é igual a R$ 500")
    void testVerificaSaldoInicialDoCartao3(){

        CartaoDto cartaoTesteSaldo = new CartaoDto();
        cartaoTesteSaldo.setNumeroCartao("11113");
        cartaoTesteSaldo.setSenha("1234");

        boolean cardCreated = cartoesService.criarCartao(cartaoTesteSaldo);


        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero("11113");
        assertEquals(cartaoInfo.get().getSaldo().getVlrSaldo()
                .subtract(ParametroGenericoEnum.SALDO_INICIAL.getValue()), BigDecimal.valueOf(0, 2));

    }

    @Test
    @DisplayName("Cartão saldo insuficiente")
    void testVerificarSaldoInsuficienteCartao(){

        CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11114");
        cartaoTeste.setSenha("123");

        boolean cardCreated = cartoesService.criarCartao(cartaoTeste);

        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero("11114");

        CartaoTransacaoDto transacaoTeste = new CartaoTransacaoDto();
        transacaoTeste.setNumeroCartao(cartaoInfo.get().getCartaoNumero());
        transacaoTeste.setSenhaCartao(cartaoInfo.get().getSenhaCartao());
        transacaoTeste.setValor(BigDecimal.valueOf(100));

        boolean saldoOK = true;

        do {
            String retorno = transacoesService.ajustarTipoTransacao(transacaoTeste);
            saldoOK = !retorno.equals(TransacaoEnum.SALDO_INSUFICIENTE.getValue());
            System.out.println(retorno);
        } while(saldoOK);

        assertFalse(false);

    }

    @Test
    @DisplayName("Transação com senha inválida")
    void testTransacaoComSenhaInvalida(){

        CartaoDto cartaoTeste = new CartaoDto();
        cartaoTeste.setNumeroCartao("11115");
        cartaoTeste.setSenha("123");

        boolean cardCreated = cartoesService.criarCartao(cartaoTeste);

        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero("11115");

        CartaoTransacaoDto transacaoTeste = new CartaoTransacaoDto();
        transacaoTeste.setNumeroCartao(cartaoInfo.get().getCartaoNumero());
        transacaoTeste.setSenhaCartao("9999999999");
        transacaoTeste.setValor(BigDecimal.valueOf(100));

        String retorno = transacoesService.ajustarTipoTransacao(transacaoTeste);
        System.out.println(retorno);
        assertEquals(retorno, TransacaoEnum.SENHA_INVALIDA.getValue());

    }
    @Test
    @DisplayName("Transação cartão inexistente")
    void test4_transacaoCartaoInexistente(){


        CartaoTransacaoDto transacaoTeste = new CartaoTransacaoDto();
        transacaoTeste.setNumeroCartao("9999999999");
        transacaoTeste.setSenhaCartao("9999999999");
        transacaoTeste.setValor(BigDecimal.valueOf(100));

        String retorno = transacoesService.ajustarTipoTransacao(transacaoTeste);
        System.out.println(retorno);
        assertEquals(retorno, TransacaoEnum.CARTAO_INEXISTENTE.getValue());

    }



}
