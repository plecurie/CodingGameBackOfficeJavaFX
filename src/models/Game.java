package models;

public class Game {

    public static Game SELECTED_GAME;
    public static int GAME_ID;
    public static String GAME_NAME;
    public static String GAME_DESCRIPTION;

    private int id;
    private String name;
    private String description;

    public Game() { /* todo default constructor */ }

    public Game(int id, String name, String description) {
        setId(id);
        setName(name);
        setDescription(description);
    }

    public static void setGameName(String gameName) {
        GAME_NAME = gameName;
    }

    public static void setGameDescription(String gameDescription) {
        GAME_DESCRIPTION = gameDescription;
    }

    public static void setSelectedGame(Game selectedGame) {
        SELECTED_GAME = selectedGame;
        GAME_ID = selectedGame.id;
        GAME_NAME = selectedGame.name;
        GAME_DESCRIPTION = selectedGame.description;
    }

    public int getId() { return id; }

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
