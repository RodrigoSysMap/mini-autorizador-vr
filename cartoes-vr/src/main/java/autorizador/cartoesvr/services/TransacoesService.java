package autorizador.cartoesvr.services;


import autorizador.cartoesvr.dto.CartaoTransacaoDto;
import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import autorizador.cartoesvr.enums.CartaoStatusEnum;
import autorizador.cartoesvr.enums.TipoTransacaoEnum;
import autorizador.cartoesvr.enums.TransacaoEnum;
import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    CartoesSaldoService cartoesSaldoService;

    public String ajustarTipoTransacao(CartaoTransacaoDto cartaoTransacaoDto){

        TipoTransacaoEnum tipoTransacao = cartaoTransacaoDto.getValor().compareTo(BigDecimal.ZERO) >= 0 ?
                TipoTransacaoEnum.DEBITO : TipoTransacaoEnum.CREDITO;

        return processarTransacao(cartaoTransacaoDto, tipoTransacao);

    }

    @Transactional
    public String processarTransacao(CartaoTransacaoDto cartaoTransacaoDto,
                                    TipoTransacaoEnum tipoTransacaoEnum ){

        String status = "";

        Optional<Cartoes> cartaoInfo = cartoesService.buscarCartaoNumero(cartaoTransacaoDto.getNumeroCartao());

        if(cartaoInfo.isEmpty()) { return TransacaoEnum.CARTAO_INEXISTENTE.getValue(); }

        if(cartaoInfo.get().getStatus().equals(CartaoStatusEnum.CARTAO_INATIVO.getValue()))
            { return TransacaoEnum.CARTAO_INATIVO.getValue(); }

        if(!cartaoInfo.get().getSenhaCartao().equals(cartaoTransacaoDto.getSenhaCartao())) {
            return TransacaoEnum.SENHA_INVALIDA.getValue(); }

        if(cartaoInfo.get().getSaldo().getVlrSaldo().compareTo(cartaoTransacaoDto.getValor()) >= 0) {

            salvarTransacao(cartaoInfo.get(), cartaoTransacaoDto.getValor(), tipoTransacaoEnum);
            return TransacaoEnum.OK.getValue();
        } else {
            return TransacaoEnum.SALDO_INSUFICIENTE.getValue();
        }

    }

    public void salvarTransacao(Cartoes cartaoInfo,
                                BigDecimal vlrTransacao,
                                TipoTransacaoEnum tipoTransacaoEnum){
        BigDecimal vlrTransacaoAjuste = tipoTransacaoEnum.equals(TipoTransacaoEnum.DEBITO) ?
                vlrTransacao.negate() : vlrTransacao;

        cartoesTransacoesRepository.save(
                CartoesTransacoes.builder().idCartao(cartaoInfo.getIdCartao()).vlrTransacao(vlrTransacaoAjuste).build());

        cartoesSaldoService.atualizarSaldoCartao(cartaoInfo);

    }
}
