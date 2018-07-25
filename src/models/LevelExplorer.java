package models;

public class LevelExplorer {
    private int id;
    private int id_levels;
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String correctAnswer;


    public LevelExplorer(int id, int idLevels, String question, String answer1, String answer2, String answer3, String correctAnswer) {
        setId(id);
        setId_levels(idLevels);
        setQuestion(question);
        setAnswer1(answer1);
        setAnswer2(answer2);
        setAnswer3(answer3);
        setCorrectAnswer(correctAnswer);
    }

    public int getId() {
        return id;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_levels() {
        return id_levels;
    }

    public void setId_levels(int id_levels) {
        this.id_levels = id_levels;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer1() {
        return answer1;
    }

    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }

    public String getAnswer2() {
        return answer2;
    }

    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }

    public String getAnswer3() {
        return answer3;
    }

    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
}
