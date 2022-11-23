package domain.order;

import java.util.Arrays;

public enum PayGroupAdvanced {
    CASH(1,1),
    CARD(2,0.95);
    private int typeNum;
    private double discountRete;
    PayGroupAdvanced(int typeNum, double discountRete) {
        this.typeNum = typeNum;
        this.discountRete = discountRete;
    }

    public static PayGroupAdvanced findByPayType(int typeNum){
        return Arrays.stream(PayGroupAdvanced.values())
                .filter(payGroup -> payGroup.hasTypeNum(typeNum))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    private boolean hasTypeNum(int typeNum){
        return this.typeNum == typeNum;
    }

    public static double discountPrice(PayGroupAdvanced payGroupAdvanced, int price){
        return price * payGroupAdvanced.discountRete;
    }
}