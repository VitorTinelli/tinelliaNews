package tinellia.news.newsflash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import tinellia.news.newsflash.requests.NewsCreationRequest;
import tinellia.news.newsflash.requests.NewsUpdateRequest;
import java.util.List;
import java.util.UUID;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class NewsServiceTest {
    News news;
    NewsCreationRequest newsCreationRequest;
    NewsUpdateRequest newsUpdateRequest;

    @InjectMocks
    private NewsService newsService;

    @Mock
    private NewsRepository newsRepository;

    @BeforeEach
    void setUp() {
        news = News.builder()
                .id(UUID.randomUUID())
                .title("test")
                .subtitle("test")
                .body("test")
                .build();
        newsCreationRequest = NewsCreationRequest.builder()
                .title("test")
                .subtitle("test")
                .body("test")
                .build();
        newsUpdateRequest = NewsUpdateRequest.builder()
                .id(UUID.randomUUID())
                .title("test")
                .subtitle("test")
                .body("test")
                .build();
    }

    @Test
    void getAllNewsShouldReturnAllNews() {
        when(newsRepository.findAll()).thenReturn(List.of(news));
        List<News> newsFound = newsService.getAllNews();
        Assertions.assertEquals(1, newsFound.size());
        verify(newsRepository).findAll();
    }

    @Test
    void findByTitleShouldReturnNews() {
        when(newsRepository.findNewsByTitle("test")).thenReturn(news);
        News newsFound = newsService.findNewsByTitle("test");
        Assertions.assertEquals(newsFound.getTitle(), "test");
        verify(newsRepository).findNewsByTitle("test");
    }

    @Test
    void findNewsByIdOrThrowExceptionShouldReturnNews() {
        when(newsRepository.findById((news.getId()))).thenReturn(Optional.of(news));
        News newsFound = newsService.findByIdOrThrowException((news.getId()));
        Assertions.assertEquals(newsFound.getId(), (news.getId()));
        verify(newsRepository).findById((news.getId()));
    }

    @Test
    void findNewsByIdOrThrowExceptionShouldReturnException() {
        when(newsRepository.findById((news.getId()))).thenThrow(new RuntimeException());
        Assertions.assertThrows(RuntimeException.class, () -> newsService.findByIdOrThrowException((news.getId())));
        verify(newsRepository).findById(news.getId());
    }

    @Test
    void createNews() {
        when(newsRepository.save(any(News.class))).thenReturn(news);
        News newsCreated = newsService.createNews(newsCreationRequest);
        Assertions.assertEquals(newsCreated, news);
    }

    @Test
    void deleteNews() {
        when(newsRepository.findById((news.getId()))).thenReturn(Optional.of(news));
        Assertions.assertDoesNotThrow(() -> newsService.deleteNews(news.getId()));
        verify(newsRepository).delete(news);
    }

    @Test
    void updateNews() {
        when(newsRepository.save(any(News.class))).thenReturn(news);
        News newsUpdated = newsService.updateNews(newsUpdateRequest);
        Assertions.assertEquals(newsUpdated, news);
    }
}
