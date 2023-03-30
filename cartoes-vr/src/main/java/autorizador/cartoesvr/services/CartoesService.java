package autorizador.cartoesvr.services;


import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.dto.CartaoTransacaoDto;
import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.entity.CartoesSaldo;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import autorizador.cartoesvr.enums.CartaoStatusEnum;
import autorizador.cartoesvr.enums.ParametroGenericoEnum;
import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class CartoesService {

    @Autowired
    CartoesRepository cartoesRepository;
    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;
    @Autowired
    CartoesTransacoesRepository cartoesTransacoesRepository;

    @Transactional
    public boolean criarCartao(CartaoDto cartaoDto){
        boolean cartaoCriado = false;

        if(!validarCartaoExiste(cartaoDto.getNumeroCartao())){
            BigDecimal saldoInicial = ParametroGenericoEnum.SALDO_INICIAL.getValue();
            Cartoes novoCartao = cartoesRepository.saveAndFlush(Cartoes.builder()
                    .cartaoNumero(cartaoDto.getNumeroCartao())
                    .saldo(null)
                    .status(CartaoStatusEnum.CARTAO_ATIVO.getValue())
                    .senhaCartao(cartaoDto.getSenha()).build());

            cartoesSaldoRepository.save(CartoesSaldo.builder().cartoes(novoCartao)
                    .vlrSaldo(saldoInicial).build());

            cartoesTransacoesRepository.save(CartoesTransacoes.builder()
                    .idCartao(novoCartao.getIdCartao())
                    .vlrTransacao(saldoInicial).build());

            cartaoCriado = true;
        }

        return cartaoCriado;
    }

    public boolean validarCartaoExiste(String numeroCartao){
        return cartoesRepository.existsByCartaoNumeroAndStatus(numeroCartao,
                CartaoStatusEnum.CARTAO_ATIVO.getValue());
    }

    public Optional<Cartoes> buscarCartaoNumero(String numeroCartao){
        return cartoesRepository.findByCartaoNumero(numeroCartao);
    }

    public BigDecimal obterSaldoCartao(String numeroCartao){
        Optional<Cartoes> cartaoInfo = cartoesRepository.findByCartaoNumero(numeroCartao);
        if(cartaoInfo.isEmpty()) { throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "");}

        return cartaoInfo.get().getSaldo().getVlrSaldo();

    }


}
