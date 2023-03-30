package autorizador.cartoesvr.enums;

import java.math.BigDecimal;

public enum ParametroGenericoEnum {


    SALDO_INICIAL(500);

    private BigDecimal value;
    ParametroGenericoEnum(int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public BigDecimal getValue() {
        return value;
    }


}
