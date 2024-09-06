package tinellia.news.newsflash.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class NewsUpdateRequest {

    @NotEmpty(message = "Id cannot be empty")
    private UUID id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "SubTitle cannot be empty")
    private String subtitle;
    @NotEmpty(message = "Body cannot be empty")
    private String body;

}
