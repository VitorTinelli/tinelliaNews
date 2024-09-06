package tinellia.news.newsflash.requests;


import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsCreationRequest {

    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "SubTitle cannot be empty")
    private String subtitle;
    @NotEmpty(message = "Body cannot be empty")
    private String body;

}
