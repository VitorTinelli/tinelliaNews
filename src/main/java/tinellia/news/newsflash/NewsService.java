package tinellia.news.newsflash;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tinellia.news.newsflash.requests.NewsCreationRequest;
import tinellia.news.newsflash.requests.NewsUpdateRequest;
import java.util.UUID;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {

    private final NewsRepository newsRepository;

    public List<News> getAllNews() { return newsRepository.findAll();}

    public News findNewsByTitle(String title) { return newsRepository.findNewsByTitle(title);}

    public News findByIdOrThrowException(UUID id) {
        return newsRepository.findById(id).orElseThrow(() -> new RuntimeException("News not found"));
    }

    public News createNews(NewsCreationRequest newsCreationRequest) {
        return newsRepository.save(News.builder()
                .title(newsCreationRequest.getTitle())
                .subtitle(newsCreationRequest.getSubtitle())
                .body(newsCreationRequest.getBody())
                .build());
    }

    public News updateNews(NewsUpdateRequest newsUpdateRequest) {
        return newsRepository.save(News.builder()
                .id(newsUpdateRequest.getId())
                .title(newsUpdateRequest.getTitle())
                .subtitle(newsUpdateRequest.getSubtitle())
                .body(newsUpdateRequest.getBody())
                .build());
    }

    public void deleteNews(UUID id) { newsRepository.delete(findByIdOrThrowException(id));}
}
