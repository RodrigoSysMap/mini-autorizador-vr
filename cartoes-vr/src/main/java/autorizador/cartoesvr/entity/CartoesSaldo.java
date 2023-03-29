package autorizador.cartoesvr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name="cartoes_saldo")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartoesSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long idSaldo;

    @Column(name="cartao_id", nullable = false)
    private Long idCartao;

    @Column(name="saldo", nullable = false)
    private BigDecimal vlrSaldo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_criacao")
    private Date dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_atualizacao")
    private Date dataAtualizacao;

}
