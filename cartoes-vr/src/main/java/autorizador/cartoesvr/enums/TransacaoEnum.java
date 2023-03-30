package autorizador.cartoesvr.enums;

public enum TransacaoEnum {

    OK("OK"),

    SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"),

    SENHA_INVALIDA("SENHA_INVALIDA"),

    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE"),

    CARTAO_INATIVO("I");

    private String value;

    TransacaoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
