package autorizador.cartoesvr.repository;

import autorizador.cartoesvr.entity.Cartoes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesRepository extends JpaRepository<Cartoes, Long> {



}
