package autorizador.cartoesvr.enums;

public enum CartaoStatusEnum {


    CARTAO_ATIVO("A"),
    CARTAO_INATIVO("I");

    private String value;
    CartaoStatusEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }


}
