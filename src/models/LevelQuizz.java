package models;

public class LevelQuizz {
    private static int id;
    private static int id_levels;
    private static String question;
    private static String answer1;
    private static String answer2;
    private static String answer3;
    private static String answer4;
    private static String correct_answer;
    private static int difficulty;


    public LevelQuizz(int id, int idLevels, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer, int difficulty) {
        setId(id);
        setId_levels(idLevels);
        setQuestion(question);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setAnswer4(answer4);
        setCorrect_answer(correctAnswer);
        setDifficulty(difficulty);
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        LevelQuizz.id = id;
    }

    public static int getId_levels() {
        return id_levels;
    }

    public static void setId_levels(int id_levels) {
        LevelQuizz.id_levels = id_levels;
    }

    public static String getQuestion() {
        return question;
    }

    public static void setQuestion(String question) {
        LevelQuizz.question = question;
    }

    public static String getAnswer1() {
        return answer1;
    }

    public static void setAnswer1(String answer1) {
        LevelQuizz.answer1 = answer1;
    }

    public static String getAnswer2() {
        return answer2;
    }

    public static void setAnswer2(String answer2) {
        LevelQuizz.answer2 = answer2;
    }

    public static String getAnswer3() {
        return answer3;
    }

    public static void setAnswer3(String answer3) {
        LevelQuizz.answer3 = answer3;
    }

    public static String getAnswer4() {
        return answer4;
    }

    public static void setAnswer4(String answer4) {
        LevelQuizz.answer4 = answer4;
    }

    public static String getCorrect_answer() {
        return correct_answer;
    }

    public static void setCorrect_answer(String correct_answer) {
        LevelQuizz.correct_answer = correct_answer;
    }

    public static int getDifficulty() {
        return difficulty;
    }

    public static void setDifficulty(int difficulty) {
        LevelQuizz.difficulty = difficulty;
    }


}
