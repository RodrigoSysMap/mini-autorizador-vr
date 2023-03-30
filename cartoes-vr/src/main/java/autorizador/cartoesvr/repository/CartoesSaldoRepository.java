package autorizador.cartoesvr.repository;

import autorizador.cartoesvr.entity.Cartoes;
import autorizador.cartoesvr.entity.CartoesSaldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartoesSaldoRepository extends JpaRepository<CartoesSaldo, Long> {

    CartoesSaldo findOneByCartoes(Cartoes cartao);

}
