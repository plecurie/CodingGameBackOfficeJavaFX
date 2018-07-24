package models;

public class History {
    private static int id_user;
    private static String game;
    private static String level;
    private static double score;
    private static String date;

    public History(int id_user, String game, String level, int score, String date) {
        setId_user(id_user);
        setGame(game);
        setLevel(level);
        setScore(score);
        setDate(date);
    }

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        History.id_user = id_user;
    }

    public static String getGame() {
        return game;
    }

    public static void setGame(String id_game) {
        History.game = id_game;
    }

    public static String getLevel() {
        return level;
    }

    public static void setLevel(String id_level) {
        History.level = id_level;
    }

    public static double getScore() {
        return score;
    }

    public static void setScore(double score) {
        History.score = score;
    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        History.date = date;
    }
}
