package tinellia.news.newsflash;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface NewsRepository extends JpaRepository<News, UUID> {
    News findNewsByTitle(String title);
}
