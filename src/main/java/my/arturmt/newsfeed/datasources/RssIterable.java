package my.arturmt.newsfeed.datasources;

import com.apptasticsoftware.rssreader.Item;
import com.apptasticsoftware.rssreader.RssReader;
import my.arturmt.newsfeed.values.News;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static my.arturmt.newsfeed.datasources.RssDataSource.DEFAULT;

public class RssIterable implements Iterable<List<News>> {

    private final String url;
    private final int times;

    public RssIterable(String url, int times) {
        this.url = url;
        this.times = times;
    }

    @Override
    public Iterator<List<News>> iterator() {
        return new RssIterator(times, url);
    }
}

class RssIterator implements Iterator<List<News>> {

    private final int times;
    private int counter = 0;
    private String url;

    public RssIterator(int times, String url) {
        this.times = times;
        this.url = url;
    }

    @Override
    public boolean hasNext() {
        if (counter++ < times) {
            return true;
        }
        return false;
    }

    @Override
    public List<News> next() {
        RssReader reader = new RssReader();
        Stream<Item> rssFeed = null;

        try {
            rssFeed = reader.read(url);
        } catch (IOException e) {
        }

        List<News> articles = rssFeed
                .map(item ->
                        new News()
                                .setId(item.getGuid().orElse(DEFAULT))
                                .setTitle(item.getTitle().orElse(DEFAULT))
                                .setDescription(item.getDescription().orElse(DEFAULT))
                )
                .collect(Collectors.toList());

        return articles;
    }
}

