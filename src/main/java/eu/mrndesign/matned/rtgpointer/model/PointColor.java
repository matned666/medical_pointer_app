package eu.mrndesign.matned.rtgpointer.model;

import javafx.scene.paint.Color;

public enum PointColor {

    BLUE,
    RED,
    YELLOW,
    GREEN,
    MAGENTA,
    CYAN,
    PINK,
    WHITE;

    static PointColor getByNum(int num){
        int number = getCorrectNum(num);
        switch (number){
            case 7: return WHITE;
            case 6: return PINK;
            case 5: return CYAN;
            case 4: return MAGENTA;
            case 3: return GREEN;
            case 2: return YELLOW;
            case 1: return RED;
            default: return BLUE;
        }
    }

    public String getColorName(){
        switch (this){
            case WHITE: return "white";
            case PINK: return "pink";
            case CYAN: return "cyan";
            case MAGENTA: return "magenta";
            case GREEN: return "green";
            case YELLOW: return "yellow";
            case RED: return "red";
            default:return "blue";
        }
    }

    public Color getColor(){
        switch (this){
            case WHITE: return Color.WHITE;
            case PINK: return Color.PINK;
            case CYAN: return Color.CYAN;
            case MAGENTA: return Color.MAGENTA;
            case GREEN: return Color.GREEN;
            case YELLOW: return Color.YELLOW;
            case RED: return Color.RED;
            default:return Color.BLUE;
        }
    }

    public int getColorId(){
        switch (this){
            case WHITE: return 7;
            case PINK: return 6;
            case CYAN: return 5;
            case MAGENTA: return 4;
            case GREEN: return 3;
            case YELLOW: return 2;
            case RED: return 1;
            default:return 0;
        }
    }

    private static int getCorrectNum(int num) {
        for (int i = 7; i > 0; i--) {
            if (num%i == 0) return i;
        }
        return 0;
    }


}
