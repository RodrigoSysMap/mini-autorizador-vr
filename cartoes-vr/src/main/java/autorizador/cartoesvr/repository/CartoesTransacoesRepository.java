package autorizador.cartoesvr.repository;

import autorizador.cartoesvr.entity.CartoesSaldo;
import autorizador.cartoesvr.entity.CartoesTransacoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartoesTransacoesRepository extends JpaRepository<CartoesTransacoes, Long> {

    List<CartoesTransacoes> findAllByIdCartao(Long idCartao);

}
