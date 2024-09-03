package tinellia.news.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Users {
    private UUID id;

    private String username;
    private String password;
    private String email;
    private String authorities;
}

