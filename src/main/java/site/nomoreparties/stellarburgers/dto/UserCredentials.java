package site.nomoreparties.stellarburgers.dto;

import lombok.Data;

@Data
public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
