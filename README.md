## News feed


### Introduction

A simple application that runs an **endless** stream of news from the source by the **rss**. 

## Installation & Run
```bash
# Download this project
./mvnw spring-boot:run
```

```
```bash
# Build and Run
cd news-feed
./mvnw package 

# API Endpoint : http://127.0.0.1:8000

#news
curl http://127.0.0.1:8080/news
curl http://127.0.0.1:8080/news/filter?filter=football
```

## Structure
```
└───src
    ├───main
    │   ├───java
    │   │   └───my
    │   │       └───arturmt
    │   │           └───newsfeed
    │   │               ├───controllers
    │   │               ├───datasources
    │   │               └───values
    │   └───resources
    └───test
        └───java
            └───my
                └───arturmt
                    └───newsfeed
```

## API

#### /news
* `GET` : Get news stream

#### /news/filter?filter={filter}
* `GET` : Get news stream filtered by {filter}