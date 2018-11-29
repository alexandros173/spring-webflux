package demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Client {

    private final static Logger logger = LoggerFactory.getLogger(Client.class);

    private static WebClient client = WebClient.create("http://localhost:8081");


    public static void main(String[] args) {


        Instant start = Instant.now();


                Flux.range(1,5)
                        .flatMap(i -> client.get().uri("/player/{id}" ,i)
                        .retrieve()
                        .bodyToMono(Player.class)
                        .flatMap( person -> client.get().uri("/player/{id}/position" , i)
                                .retrieve()
                                .bodyToMono(Position.class)))
                        .blockLast();


        logger.debug("elapsed time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
    }
}
