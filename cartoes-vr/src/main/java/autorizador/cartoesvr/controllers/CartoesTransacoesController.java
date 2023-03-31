package autorizador.cartoesvr.controllers;


import autorizador.cartoesvr.dto.CartaoTransacaoDto;
import autorizador.cartoesvr.enums.TransacaoEnum;
import autorizador.cartoesvr.services.TransacoesService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/transacoes")
@Tag(name="Transações Manutenção", description = "Operações com cartões e suas transações")
public class CartoesTransacoesController {

    @Autowired
    private TransacoesService transacoesService;


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação realizada com sucesso",
                    content = { @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = CartaoTransacaoDto.class)) }),
            @ApiResponse(responseCode = "422",
                    description = "Não foi possivel realizar a transação. Status:  SALDO_INSUFICIENTE | SENHA_INVALIDA | CARTAO_INEXISTENTE ",
                    content = @Content(mediaType = "text/plain",
                            schema = @Schema(implementation = CartaoTransacaoDto.class)))})
    public ResponseEntity<?> processarTransacao(@Valid @RequestBody CartaoTransacaoDto cartaoTransacaoDto){
        String status = transacoesService.ajustarTipoTransacao(cartaoTransacaoDto);
        return new ResponseEntity<>(status , status.equals(TransacaoEnum.OK.getValue()) ?
                HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);

    }


}
