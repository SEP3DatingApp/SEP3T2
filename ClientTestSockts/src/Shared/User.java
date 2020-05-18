package Shared;

public class User
{
    public int UserId;
    public String userType;
    public String Username;
    public String Password;
    public String Email;
    public String Gender;
    public String SexPref;
    public String PicRef;
    public int Age;
    public boolean IsActive;
    public String Name;
    public String Description;
    public boolean isActive;

    public User(int userId, String userType, String username, String password, String email, String gender, String sexPref, String picRef, int age, boolean isActive, String name, String description, boolean isActive1)
    {
        UserId = userId;
        this.userType = userType;
        Username = username;
        Password = password;
        Email = email;
        Gender = gender;
        SexPref = sexPref;
        PicRef = picRef;
        Age = age;
        IsActive = isActive;
        Name = name;
        Description = description;
        this.isActive = isActive1;
    }


    @Override
    public String toString()
    {
        return "User{" + "UserId=" + UserId + ", userType='" + userType + '\'' + ", Username='" + Username + '\'' + ", Password='" + Password + '\'' + ", Email='" + Email + '\'' + ", Gender='" + Gender + '\'' + ", SexPref='" + SexPref + '\'' + ", PicRef='" + PicRef + '\'' + ", Age=" + Age + ", IsActive=" + IsActive + ", Name='" + Name + '\'' + ", Description='" + Description + '\'' + ", isActive=" + isActive + '}';
    }
}
