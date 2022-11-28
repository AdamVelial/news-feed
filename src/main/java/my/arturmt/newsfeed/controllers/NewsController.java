package my.arturmt.newsfeed.controllers;

import lombok.RequiredArgsConstructor;
import my.arturmt.newsfeed.datasources.RssDataSource;
import my.arturmt.newsfeed.values.News;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class NewsController {
    private final RssDataSource dataSource;

    @GetMapping(path = "/news", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<List<News>> getNews() {
        return dataSource.asyncFetch();
    }

    @GetMapping(path = "/news/filter", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<List<News>> getFilteredNews(@RequestParam("filter") String filter) {
        if (filter == null) {
            return Flux.empty();
        } else {
            return dataSource.asyncFetch().doOnEach(signal -> {
                Objects.requireNonNull(signal.get())
                        .removeIf(n -> n.getTitle().contains(filter));
            });
        }
    }
}
