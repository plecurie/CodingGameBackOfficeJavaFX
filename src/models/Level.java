package models;

public class Level {
    public static Level SELECTED_LEVEL;
    public static int LEVEL_ID;
    public static String LEVEL_NAME;
    public static int LEVEL_DIFFICULTY;

    private int id;
    private String name;
    private int difficulty;

    public Level() {/*todo default constructor;*/}

    public Level(int id, String name, int difficulty) {
        setId(id);
        setName(name);
        setDifficulty(difficulty);
    }

    public static void setSelectedLevel(Level selectedLevel) {
        SELECTED_LEVEL = selectedLevel;
        LEVEL_ID = selectedLevel.id;
        LEVEL_NAME = selectedLevel.name;
        LEVEL_DIFFICULTY = selectedLevel.difficulty;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getDifficulty() { return difficulty; }
    private void setDifficulty(int difficulty) { this.difficulty = difficulty; }
}
