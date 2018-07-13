package settings;

public class Colors {

    public static final String BLACK = "-fx-background-color:BLACK;";
    public static final String RED = "-fx-background-color:RED;";
    public static final String GREEN = "-fx-background-color:LIMEGREEN;";
    public static final String BLUE = "-fx-background-color:BLUE;";
    public static final String LIGHTGREY = "-fx-background-color:LIGHTGREY;";
    public static final String WHITESMOKE = "-fx-background-color:WHITESMOKE;";

    public static String convertColor(String color){
        String stringColor = "";

        if (color.equals("BLACK")) stringColor = BLACK;
        if (color.equals("BLUE")) stringColor = BLUE;
        if (color.equals("GREEN")) stringColor = GREEN;
        if (color.equals("RED")) stringColor = RED;

        return stringColor;
    }

}
