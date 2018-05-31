package models;

public class User {
    private static int id ;
    private static String username ;
    private static String password ;
    private static String firstname ;
    private static String lastname ;
    private static int age ;
    private static String email ;
    private static String profil ;
    private static int level ;
    private static float exp ;
    private static String type ;

    public User(){ /*todo default constructor*/ }

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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        User.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getFirstname() {
        return firstname;
    }

    public static void setFirstname(String firstname) {
        User.firstname = firstname;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        User.lastname = lastname;
    }

    public static int getAge() {
        return age;
    }

    public static void setAge(int age) {
        User.age = age;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getProfil() {
        return profil;
    }

    public static void setProfil(String profil) {
        User.profil = profil;
    }

    public static int getLevel() {
        return level;
    }

    public static void setLevel(int level) {
        User.level = level;
    }

    public static float getExp() {
        return exp;
    }

    public static void setExp(float exp) {
        User.exp = exp;
    }

    public static String getType() {
        return type;
    }

    public static void setType(String type) {
        User.type = type;
    }



}
