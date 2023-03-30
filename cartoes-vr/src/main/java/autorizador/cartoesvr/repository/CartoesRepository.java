package autorizador.cartoesvr.repository;

import autorizador.cartoesvr.entity.Cartoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartoesRepository extends JpaRepository<Cartoes, Long> {

    boolean existsByCartaoNumeroAndStatus(String cartaoNumero, String status);
    Optional<Cartoes> findByCartaoNumero(String cartaoNumero);

}
