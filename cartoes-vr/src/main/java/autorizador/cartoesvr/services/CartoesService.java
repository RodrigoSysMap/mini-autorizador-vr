package autorizador.cartoesvr.services;


import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartoesService {

    @Autowired
    CartoesRepository cartoesRepository;

    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;


}
