package autorizador.cartoesvr.enums;

public enum TipoTransacaoEnum {

    CREDITO("CREDITO"),

    DEBITO("DEBITO");

    private String value;

    TipoTransacaoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
