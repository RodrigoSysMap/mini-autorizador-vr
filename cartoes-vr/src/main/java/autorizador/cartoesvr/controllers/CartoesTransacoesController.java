package autorizador.cartoesvr.controllers;


import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.dto.CartaoTransacaoDto;
import autorizador.cartoesvr.enums.TransacaoEnum;
import autorizador.cartoesvr.services.CartoesService;
import autorizador.cartoesvr.services.TransacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transacoes")
public class CartoesTransacoesController {

    @Autowired
    private TransacoesService transacoesService;


    @PostMapping
    public ResponseEntity<?> processarTransacao(@Valid @RequestBody CartaoTransacaoDto cartaoTransacaoDto){
        String status = transacoesService.ajustarTipoTransacao(cartaoTransacaoDto);
        return new ResponseEntity<>(status , status.equals(TransacaoEnum.OK.getValue()) ?
                HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);

    }


}
