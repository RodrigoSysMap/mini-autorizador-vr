package autorizador.cartoesvr.controllers;


import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.services.CartoesService;
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
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/v1/cartoes")
@Tag(name="Cartões Manutenção", description = "Operações com cartões")
public class CartoesController {

    @Autowired
    private CartoesService cartoesService;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cartão criado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CartaoDto.class)) }),
            @ApiResponse(responseCode = "422",
                    description = "Cartão já cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CartaoDto.class)))})
    public ResponseEntity<CartaoDto> criarCartao(@Valid @RequestBody CartaoDto cartaoDto){
        boolean created = cartoesService.criarCartao(cartaoDto);
        return new ResponseEntity<>(cartaoDto , created ?
                HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @GetMapping(value = "/{numeroCartao}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cartão encontrado com sucesso, retornando o valor do saldo",
                    content = { @Content(mediaType = "text/plain") }),
            @ApiResponse(responseCode = "404",
                    description = "Cartão não encontrado")})
    public ResponseEntity<?> obterSaldoCartao(@PathVariable String numeroCartao){
        return new ResponseEntity<>(cartoesService.obterSaldoCartao(numeroCartao) , HttpStatus.OK );

    }

}
