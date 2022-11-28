package my.arturmt.newsfeed.datasources;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import my.arturmt.newsfeed.values.News;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@Configuration
@Slf4j
public class RssDataSource {

    public static final String DEFAULT = "None";

    @Value("${news.repeats}")
    public int repeats;

    @Value("${news.rss-url}")
    public String urls;

    @PostConstruct
    public void init() {
        log.debug("rss-url: {}", urls);
    }

    public Flux<List<News>> asyncFetch() {
        return Flux.<List<News>>fromIterable(new RssIterable(urls, repeats));
    }
}

