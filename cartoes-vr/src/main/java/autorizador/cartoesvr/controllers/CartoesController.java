package autorizador.cartoesvr.controllers;


import autorizador.cartoesvr.dto.CartaoDto;
import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.services.CartoesService;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class CartoesController {

    @Autowired
    private CartoesService cartoesService;

    @PostMapping
    public ResponseEntity<CartaoDto> criarCartao(@Valid @RequestBody CartaoDto cartaoDto){
        boolean created = cartoesService.criarCartao(cartaoDto);
        return new ResponseEntity<>(cartaoDto , created ?
                HttpStatus.CREATED : HttpStatus.UNPROCESSABLE_ENTITY);

    }

    @GetMapping(value = "/{numeroCartao}")
    public ResponseEntity<?> obterSaldoCartao(@PathVariable String numeroCartao){
        return new ResponseEntity<>(cartoesService.obterSaldoCartao(numeroCartao) , HttpStatus.OK );

    }

}
