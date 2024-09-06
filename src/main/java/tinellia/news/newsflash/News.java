package tinellia.news.newsflash;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class News {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String title;
    private String subtitle;
    private String body;
}