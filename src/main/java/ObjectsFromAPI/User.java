package ObjectsFromAPI;

public class User {
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

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getSexPref() {
        return SexPref;
    }

    public void setSexPref(String sexPref) {
        SexPref = sexPref;
    }

    public String getPicRef() {
        return PicRef;
    }

    public void setPicRef(String picRef) {
        PicRef = picRef;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setisActive(boolean isActive) {
        this.isActive = isActive;
    }
}
