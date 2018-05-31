package models;

public class Game {
    private static int id;
    private static String name;
    private static String description;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Game.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Game.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        Game.description = description;
    }
}
