package tinellia.news.repository;

import org.springframework.stereotype.Repository;
import tinellia.news.domain.Users;

@Repository
public interface UsersRepository {
    Users findByUsername(String username);

    Users findByEmail(String email);
}
