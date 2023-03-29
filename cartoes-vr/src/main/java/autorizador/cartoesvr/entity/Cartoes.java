package autorizador.cartoesvr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name="cartoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cartoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false, unique = true)
    private Long idCartao;

    @Column(name="cartao_numero", nullable = false)
    @NotNull(message = "Informe o número do cartão a ser cadastrado")
    private String cartaoNumero;

    @Column(name="senha", nullable = false, length = 6)
    @NotNull(message = "Informe o número da senha no cartão")
    private String senhaCartao;

    @Column(name="status", nullable = false)
    private String status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_criacao")
    private Date dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_atualizacao")
    private Date dataAtualizacao;

}
