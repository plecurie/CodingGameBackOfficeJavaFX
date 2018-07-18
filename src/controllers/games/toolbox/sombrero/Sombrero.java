package controllers.games.toolbox.sombrero;

import javafx.scene.layout.GridPane;

public class Sombrero {


    private static Sombrero SELECTED_SOMBRERO;
    private static GridPane gridpane;
    static String name;
    static int difficulty;
    private static int f1, f2, f3, f4, cellCount;

    private static final double INNER_ITEM_DEFAULT_HEIGHT = 30;
    private static final double INNER_ITEM_DEFAULT_WIDTH = 30;

    public Sombrero(GridPane sombrero_gridpane, String name, int f1, int f2, int f3, int f4, int difficulty, int cellCount) {
        setGridpane(sombrero_gridpane);
        setName(name);
        setF1(f1);
        setF2(f2);
        setF3(f3);
        setF4(f4);
        setDifficulty(difficulty);
        setCellCount(cellCount);
    }

    public static void setName(String name) { Sombrero.name = name; }
    private static void setGridpane(GridPane gridpane) { Sombrero.gridpane = gridpane; }
    private static void setF1(int f1) { Sombrero.f1 = f1; }
    private static void setF2(int f2) { Sombrero.f2 = f2; }
    private static void setF3(int f3) { Sombrero.f3 = f3; }
    private static void setF4(int f4) { Sombrero.f4 = f4; }
    private static void setDifficulty(int difficulty) { Sombrero.difficulty = difficulty; }
    private static void setCellCount(int cellCount) { Sombrero.cellCount = cellCount; }
    public static Sombrero getSelectedSombrero() { return SELECTED_SOMBRERO; }
    public static void setSelectedSombrero(Sombrero selectedSombrero) { SELECTED_SOMBRERO = selectedSombrero; }

    public String getName() { return name; }
    GridPane getGridpane() { return gridpane; }
    int getF1() { return f1; }
    int getF2() { return f2; }
    int getF3() { return f3; }
    int getF4() { return f4; }
    int getDifficulty() { return difficulty; }
    int getCellCount() { return cellCount; }

    static double getINNER_ITEM_DEFAULT_HEIGHT() { return INNER_ITEM_DEFAULT_HEIGHT; }
    static double getINNER_ITEM_DEFAULT_WIDTH() { return INNER_ITEM_DEFAULT_WIDTH; }
}
