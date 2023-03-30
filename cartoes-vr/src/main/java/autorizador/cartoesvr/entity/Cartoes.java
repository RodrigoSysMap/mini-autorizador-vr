package autorizador.cartoesvr.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name="cartoes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cartoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long idCartao;

    @Size(min = 16, max = 16 , message = "Número do cartão fora do padrão.")
    @Column(name="cartao_numero", nullable = false)
    @NotNull(message = "Informe o número do cartão a ser cadastrado")
    private String cartaoNumero;

    @Column(name="senha", nullable = false, length = 6)
    @NotNull(message = "Informe o número da senha no cartão")
    private String senhaCartao;

    @Column(name="status", nullable = false)
    private String status;

    @OneToOne(mappedBy = "cartoes", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private CartoesSaldo saldo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_criacao")
    private Date dataCriacao;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="data_atualizacao")
    private Date dataAtualizacao;

}
