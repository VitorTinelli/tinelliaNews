package tinellia.news.users;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<Users, UUID> {
    Users findByUsername(String username);

    Users findByEmail(String email);
}
