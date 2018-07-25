package models;

public class User {

    private static  int USER_ID;
    private static String USERNAME ;
    private static String FIRSTNAME ;
    private static String LASTNAME ;
    private static String AGE ;
    private static String EMAIL ;
    private static String LEVEL ;
    private static String EXP ;
    private static String TYPE ;
    private static String TOKEN;

    private static User SELECTED_USER;

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

    public static void setSelectedUser(User selected_user) {
        SELECTED_USER = selected_user;
    }

    public static User getSelectedUser() {
        return SELECTED_USER;
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

    public static String getTOKEN() { return TOKEN; }
    public static void setTOKEN(String TOKEN) { User.TOKEN = TOKEN; }

}
