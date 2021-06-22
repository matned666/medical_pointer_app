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
    WHITE,
    BLACK;

    static PointColor getByNum(int num){
        int number = getCorrectNum(num);
        switch (number){
            case 7: return WHITE;
            case 6: return BLUE;
            case 5: return CYAN;
            case 4: return MAGENTA;
            case 3: return GREEN;
            case 2: return YELLOW;
            case 1: return RED;
            default: return BLACK;
        }
    }

    public String getColorName(){
        switch (this){
            case WHITE: return "white";
            case BLUE: return "blue";
            case CYAN: return "cyan";
            case MAGENTA: return "magenta";
            case GREEN: return "green";
            case YELLOW: return "yellow";
            case RED: return "red";
            default:return "black";
        }
    }

    public Color getColor(){
        switch (this){
            case WHITE: return Color.WHITE;
            case BLUE: return Color.BLUE;
            case CYAN: return Color.CYAN;
            case MAGENTA: return Color.MAGENTA;
            case GREEN: return Color.GREEN;
            case YELLOW: return Color.YELLOW;
            case RED: return Color.RED;
            default:return Color.BLACK;
        }
    }

    public int getColorId(){
        switch (this){
            case WHITE: return 7;
            case BLUE: return 6;
            case CYAN: return 5;
            case MAGENTA: return 4;
            case GREEN: return 3;
            case YELLOW: return 2;
            case RED: return 1;
            default:return 0;
        }
    }

    private static int getCorrectNum(int num) {
        int number = num%7;
        if (number == 0) number = 7;
        return number;
    }


}
