package settings;

public class Colors {

    public static final String BLACK = "-fx-background-color: black;";
    public static final String RED = "-fx-background-color: red;";
    public static final String GREEN = "-fx-background-color: limegreen;";
    public static final String BLUE = "-fx-background-color: blue;";
    public static final String LIGHTGREY = "-fx-background-color: lightgrey;";
    public static final String SILVER = "-fx-background-color: silver;";
    public static final String WHITESMOKE = "-fx-background-color: whitesmoke;";

    public static String convertColor(String color){
        String stringColor = "";

        if (color.equals("BLACK")) stringColor = BLACK;
        if (color.equals("BLUE")) stringColor = BLUE;
        if (color.equals("GREEN")) stringColor = GREEN;
        if (color.equals("RED")) stringColor = RED;

        return stringColor;
    }

}
