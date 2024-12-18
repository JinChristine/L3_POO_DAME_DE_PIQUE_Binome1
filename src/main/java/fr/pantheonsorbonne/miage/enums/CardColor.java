package fr.pantheonsorbonne.miage.enums;

public enum CardColor {
    SPADE(127137),
    HEART(127137 + 16),
    DIAMOND(127137 + 16 * 2),
    CLUB(127137 + 16 * 3);

    private final int code;
    CardColor(int code){
        this.code = code;
    }

    

    public int getCode(){
        return code;
    }

    public String getStringRepresentation(CardColor color) {
        return color.toString();
    }
}