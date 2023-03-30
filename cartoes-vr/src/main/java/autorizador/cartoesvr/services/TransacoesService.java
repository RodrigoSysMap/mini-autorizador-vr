package autorizador.cartoesvr.services;


import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.enums.CartaoStatusEnum;
import autorizador.cartoesvr.enums.TipoTransacaoEnum;
import autorizador.cartoesvr.enums.TransacaoEnum;
import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class TransacoesService {

    @Autowired
    CartoesRepository cartoesRepository;

    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;

    @Autowired
    CartoesTransacoesRepository cartoesTransacoesRepository;

    @Autowired
    CartoesService cartoesService;

    public String realizarTransacao(String numeroCartao,
                                    String senhaCartao,
                                    BigDecimal vlrTransacao,
                                    TipoTransacaoEnum tipoTransacaoEnum ){

        String status = "";

        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero(numeroCartao);

        if(cartaoInfo.isEmpty()) { return TransacaoEnum.CARTAO_INEXISTENTE.getValue(); }

        if(cartaoInfo.get().getStatus().equals(CartaoStatusEnum.CARTAO_INATIVO.getValue()))
            { return TransacaoEnum.CARTAO_INEXISTENTE.getValue(); }

        if(!cartaoInfo.get().getSenhaCartao().equals(senhaCartao)) { return TransacaoEnum.SENHA_INVALIDA.getValue(); }
        if(!cartaoInfo.get().equals(senhaCartao)) { return TransacaoEnum.SENHA_INVALIDA.getValue(); }

        return status;


    }


}
