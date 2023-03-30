package autorizador.cartoesvr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartoesSaldo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idSaldo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "cartao_id")
    private Cartoes cartoes;

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
