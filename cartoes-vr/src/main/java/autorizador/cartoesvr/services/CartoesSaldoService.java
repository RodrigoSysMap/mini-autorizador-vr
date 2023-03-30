package autorizador.cartoesvr.services;


import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.entity.CartoesSaldo;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartoesSaldoService {

    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;

    @Autowired
    CartoesTransacoesRepository cartoesTransacoesRepository;

    public void atualizarSaldoCartao(Cartoes cartaoInfo){

        List<CartoesTransacoes> transacoesCartao = cartoesTransacoesRepository
                .findAllByIdCartao(cartaoInfo.getIdCartao());

        BigDecimal somaSaldo = transacoesCartao.stream().map(CartoesTransacoes::getVlrTransacao)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        CartoesSaldo ajusteSaldo = cartaoInfo.getSaldo();
        ajusteSaldo.setVlrSaldo(somaSaldo);

        cartoesSaldoRepository.save(ajusteSaldo);
    }


}
