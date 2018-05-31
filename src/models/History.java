package models;

import java.util.Date;

public class History {
    private static int id;
    private static int id_user;
    private static int id_game;
    private static int id_level;
    private static double score;
    private static Date date;

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        History.id = id;
    }

    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        History.id_user = id_user;
    }

    public static int getId_game() {
        return id_game;
    }

    public static void setId_game(int id_game) {
        History.id_game = id_game;
    }

    public static int getId_level() {
        return id_level;
    }

    public static void setId_level(int id_level) {
        History.id_level = id_level;
    }

    public static double getScore() {
        return score;
    }

    public static void setScore(double score) {
        History.score = score;
    }

    public static Date getDate() {
        return date;
    }

    public static void setDate(Date date) {
        History.date = date;
    }
}
