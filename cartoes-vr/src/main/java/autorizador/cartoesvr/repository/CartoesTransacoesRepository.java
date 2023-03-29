package autorizador.cartoesvr.repository;

import autorizador.cartoesvr.entity.CartoesSaldo;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesTransacoesRepository extends JpaRepository<CartoesTransacoes, Long> {



}
