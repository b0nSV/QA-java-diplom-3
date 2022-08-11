package site.nomoreparties.stellarburgers.dto;

import lombok.Data;

@Data
public class UserLoginResponse {
    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
}
