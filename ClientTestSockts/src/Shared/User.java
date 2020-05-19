package Shared;

public class User
{

    public String Username;
    public String Password;
    public String Email;
    public char Gender;
    public char SexPref;
    public String FirstName;
    public String Surname;

    public int Age;

    public String Description;


    public User(String username, String password, String email, char gender, char sexPref, String firstName, String surname, int age, String description)
    {
        Username = username;
        Password = password;
        Email = email;
        Gender = gender;
        SexPref = sexPref;
        FirstName = firstName;
        Surname = surname;
        Age = age;
        Description = description;
    }

    @Override
    public String toString()
    {
        return "User{" + "Username='" + Username + '\'' + ", Password='" + Password + '\'' + ", Email='" + Email + '\'' + ", Gender=" + Gender + ", SexPref=" + SexPref + ", FirstName='" + FirstName + '\'' + ", Surname='" + Surname + '\'' + ", Age=" + Age + ", Description='" + Description + '\'' + '}';
    }
}
