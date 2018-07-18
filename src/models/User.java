package models;

public class User {

    private static String USERNAME ;
    private static String FIRSTNAME ;
    private static String LASTNAME ;
    private static String AGE ;
    private static String EMAIL ;
    private static String LEVEL ;
    private static String EXP ;
    private static String TYPE ;
    private static String TOKEN;

    private int id ;
    private String username ;
    private String firstname ;
    private String lastname ;
    private int age ;
    private String email ;
    private String profil ;
    private int level ;
    private float exp ;
    private String type ;

    public User() {/*todo default constructor;*/ }

    public User(int id, String username, String firstname, String lastname, int age, String email, String profil, int level, float exp, String type) {
        setId(id);
        setUsername(username);
        setFirstname(firstname);
        setLastname(lastname);
        setAge(age);
        setEmail(email);
        setProfil(profil);
        setLevel(level);
        setExp(exp);
        setType(type);
    }

    public int getId() {
        return id;
    }
    private void setId(int id) { this.id = id; }
    public String getUsername() {
        return username;
    }
    private void setUsername(String username) {
        this.username = username;
    }
    public String getFirstname() {
        return firstname;
    }
    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    private void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public int getAge() {
        return age;
    }
    private void setAge(int age) {
        this.age = age;
    }
    public String getEmail() {
        return email;
    }
    private void setEmail(String email) {
        this.email = email;
    }
    public String getProfil() {
        return profil;
    }
    private void setProfil(String profil) {
        this.profil = profil;
    }
    public int getLevel() {
        return level;
    }
    private void setLevel(int level) { this.level = level; }
    public float getExp() { return exp; }
    private void setExp(float exp) { this.exp = exp; }
    public String getType() { return type; }
    private void setType(String type) { this.type = type; }

    public static String getUSERNAME() { return USERNAME; }
    public static void setUSERNAME(String USERNAME) { User.USERNAME = USERNAME; }
    public static String getFIRSTNAME() { return FIRSTNAME; }
    public static void setFIRSTNAME(String FIRSTNAME) { User.FIRSTNAME = FIRSTNAME; }
    public static String getLASTNAME() { return LASTNAME; }
    public static void setLASTNAME(String LASTNAME) { User.LASTNAME = LASTNAME; }
    public static String getAGE() { return AGE; }
    public static void setAGE(String AGE) { User.AGE = AGE; }
    public static String getEMAIL() { return EMAIL; }
    public static void setEMAIL(String EMAIL) { User.EMAIL = EMAIL; }
    public static String getLEVEL() { return LEVEL; }
    public static void setLEVEL(String LEVEL) { User.LEVEL = LEVEL; }
    public static String getEXP() { return EXP; }
    public static void setEXP(String EXP) { User.EXP = EXP; }
    public static String getTYPE() { return TYPE; }
    public static void setTYPE(String TYPE) { User.TYPE = TYPE; }
    public static String getTOKEN() { return TOKEN; }
    public static void setTOKEN(String TOKEN) { User.TOKEN = TOKEN; }

}
