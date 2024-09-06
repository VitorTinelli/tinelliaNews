package tinellia.news.newsflash;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tinellia.news.newsflash.requests.NewsCreationRequest;
import tinellia.news.newsflash.requests.NewsUpdateRequest;
import java.util.UUID;
import java.util.List;

@RestController
@RequestMapping("/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping
    public List<News> getAllNews() { return newsService.getAllNews(); }

    @GetMapping("/find/{title}")
    public News findNewsByTitle(@PathVariable String title) { return newsService.findNewsByTitle(title); }

    @GetMapping("/{id}")
    public News findByIdOrThrowException(@PathVariable UUID id) {
        return newsService.findByIdOrThrowException(id);
    }

    @PostMapping
    public News createNews(@RequestBody @Valid NewsCreationRequest newsCreationRequest) {
        return newsService.createNews(newsCreationRequest);
    }

    @PutMapping("/{id}")
    public News updateNews(@RequestBody @Valid NewsUpdateRequest newsUpdateRequest) {
        return newsService.updateNews(newsUpdateRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable UUID id) { newsService.deleteNews(id); }
}
