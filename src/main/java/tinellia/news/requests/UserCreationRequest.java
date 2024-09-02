package tinellia.news.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserCreationRequest {

    @NotEmpty(message = "Username cannot be empty")
    private String username;
    @NotEmpty(message = "Password cannot be empty")
    private String password;
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;
    private String authorities;
}
