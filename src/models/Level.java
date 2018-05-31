package models;

public class Level {
    private static int id;
    private static int id_game;
    private static String name;
    private static String description;
    private static String difficulty;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Level.id = id;
    }

    public static int getId_game() {
        return id_game;
    }

    public static void setId_game(int id_game) {
        Level.id_game = id_game;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Level.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Level.description = description;
    }

    public static String getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(String difficulty) {
        Level.difficulty = difficulty;
    }
}
