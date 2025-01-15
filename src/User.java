import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class User {
    private String username;
    private String password;
    private double beginBalance;

    public User(String username, String password, double beginAmount) {
        this.username = username;
        this.password = password;
        this.beginBalance = beginAmount;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBeginAmount() {
        return beginBalance;
    }


}



