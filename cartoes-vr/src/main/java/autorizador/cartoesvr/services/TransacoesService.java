package autorizador.cartoesvr.services;


import autorizador.cartoesvr.repository.CartoesRepository;
import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import autorizador.cartoesvr.repository.CartoesTransacoesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransacoesService {

    @Autowired
    CartoesRepository cartoesRepository;

    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;

    @Autowired
    CartoesTransacoesRepository cartoesTransacoesRepository;


}
