package tinellia.news.newsflash;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;
import java.util.UUID;


@ExtendWith(MockitoExtension.class)
class NewsControllerTest {
    News news;

    @InjectMocks
    private NewsController newsController;

    @Mock
    private NewsService newsService;

    @BeforeEach
    void setUp() {
        news = News.builder()
                .id(UUID.randomUUID())
                .title("test")
                .subtitle("test")
                .body("test")
                .build();
    }

    @Test
    void getAllNews() {
        when(newsService.getAllNews()).thenReturn(List.of(news));
        List<News> newsFound = newsController.getAllNews();
        Assertions.assertEquals(1, newsFound.size());
        verify(newsService).getAllNews();
    }

    @Test
    void findByIdOrThrowException() {
        when(newsService.findByIdOrThrowException(news.getId())).thenReturn(news);
        News newsFound = newsController.findByIdOrThrowException(news.getId());
        Assertions.assertEquals(newsFound, news);
        verify(newsService).findByIdOrThrowException(news.getId());
    }

    @Test
    void findNewsByTitle() {
        when(newsService.findNewsByTitle("test")).thenReturn(news);
        News newsFound = newsController.findNewsByTitle("test");
        Assertions.assertEquals("test", newsFound.getTitle());
        verify(newsService).findNewsByTitle("test");
    }

    @Test
    void createNews() {
        when(newsService.createNews(any())).thenReturn(news);
        News newsCreated = newsController.createNews(any());
        Assertions.assertEquals(newsCreated, news);
        verify(newsService).createNews(any());
    }

    @Test
    void deleteNews() {
        Assertions.assertDoesNotThrow(() -> newsController.deleteNews(news.getId()));
        verify(newsService).deleteNews(news.getId());
    }

    @Test
    void updateNews() {
        when(newsService.updateNews(any())).thenReturn(news);
        News newsUpdated = newsController.updateNews(any());
        Assertions.assertEquals(newsUpdated, news);
        verify(newsService).updateNews(any());
    }
}
