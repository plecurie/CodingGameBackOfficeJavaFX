package models;

public class Game {

    private static int GAME_ID;
    private static String GAME_NAME;
    private static String GAME_DESCRIPTION;

    private int id;
    private String name;
    private String description;

    public Game() { /* todo default constructor */ }

    public Game(int id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public static int getGameId() {
        return GAME_ID;
    }

    public static void setGameId(int gameId) {
        GAME_ID = gameId;
    }

    public static String getGameName() {
        return GAME_NAME;
    }

    public static void setGameName(String gameName) {
        GAME_NAME = gameName;
    }

    public static String getGameDescription() {
        return GAME_DESCRIPTION;
    }

    public static void setGameDescription(String gameDescription) {
        GAME_DESCRIPTION = gameDescription;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    private void setDescription(String description) {
        this.description = description;
    }


}
