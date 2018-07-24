package controllers.games.toolbox.sombrero;

import javafx.scene.layout.GridPane;

public class Sombrero {

    static Sombrero SELECTED_SOMBRERO;
    static GridPane gridpane;
    static String name;
    static int difficulty;
    static int f1, f2, f3, f4, cellCount;

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

    public static Sombrero getSelectedSombrero() {
        return SELECTED_SOMBRERO;
    }

    public static void setSelectedSombrero(Sombrero selectedSombrero) {
        SELECTED_SOMBRERO = selectedSombrero;
    }

    public GridPane getGridpane() {
        return gridpane;
    }

    public void setGridpane(GridPane gridpane) {
        Sombrero.gridpane = gridpane;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Sombrero.name = name;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        Sombrero.difficulty = difficulty;
    }

    public int getF1() {
        return f1;
    }

    public void setF1(int f1) {
        Sombrero.f1 = f1;
    }

    public int getF2() {
        return f2;
    }

    public void setF2(int f2) {
        Sombrero.f2 = f2;
    }

    public int getF3() {
        return f3;
    }

    public void setF3(int f3) {
        Sombrero.f3 = f3;
    }

    public int getF4() {
        return f4;
    }

    public void setF4(int f4) {
        Sombrero.f4 = f4;
    }

    public int getCellCount() {
        return cellCount;
    }

    public void setCellCount(int cellCount) {
        Sombrero.cellCount = cellCount;
    }

    public static double getInnerItemDefaultHeight() {
        return INNER_ITEM_DEFAULT_HEIGHT;
    }

    public static double getInnerItemDefaultWidth() {
        return INNER_ITEM_DEFAULT_WIDTH;
    }



}
