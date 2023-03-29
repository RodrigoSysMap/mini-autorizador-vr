package autorizador.cartoesvr.services;


import autorizador.cartoesvr.repository.CartoesSaldoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartoesSaldoService {

    @Autowired
    CartoesSaldoRepository cartoesSaldoRepository;


}
