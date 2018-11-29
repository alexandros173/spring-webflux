package com.remote.remoteService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.*;

@SpringBootApplication
public class RemoteServiceApplication {

	private static final Map<Long, Map<String, Object>> PLAYER_DATA = initPlayerData();

	private static final Map<Long, Map<String, Object>> POSITION_DATA = initPositionData();

	private static final Mono<ServerResponse> NOT_FOUND = ServerResponse.notFound().build();

	public static void main(String[] args) {

		SpringApplication.run(RemoteServiceApplication.class, args);
	}


	@Bean
	public RouterFunction<?> routes() {
		return RouterFunctions.route()
				.GET("/player/{id}", request -> {
					Long id = Long.parseLong(request.pathVariable("id"));
					Map<String, Object> body = PLAYER_DATA.get(id);
					return body != null ? ServerResponse.ok().syncBody(body) : NOT_FOUND;
				})
				.GET("/player/{id}/position", request -> {
					Long id = Long.parseLong(request.pathVariable("id"));
					Map<String, Object> body = POSITION_DATA.get(id);
					return body != null ? ServerResponse.ok().syncBody(body) : NOT_FOUND;
				})
				.GET("/players", request -> {
					List<Map<String, Object>> body = new ArrayList<>(PLAYER_DATA.values());
					return ServerResponse.ok().syncBody(body);
				})
				.build();
	}


	private static Map<Long, Map<String, Object>> initPlayerData(){
		Map<Long, Map<String, Object>> map = new HashMap<>();
		addPlayer(map, 1L, "Benedetto");
		addPlayer(map, 2L, "Wanchope");
		addPlayer(map, 3L, "Barrios");
		addPlayer(map, 4L, "Tevez");
		addPlayer(map, 5L, "Gago");
		return map;
	}


	private static void addPlayer(Map<Long, Map<String, Object>> persons, Long id, String name) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("id", id);
		map.put("name", name);
		persons.put(id, map);
	}

	private static Map<Long, Map<String, Object>> initPositionData(){
		Map<Long, Map<String, Object>> map = new HashMap<>();
		addPosition(map, 1L, "Striker");
		addPosition(map, 2L, "Striker");
		addPosition(map, 3L, "Midfielder");
		addPosition(map, 4L, "Attacking Midfielder");
		addPosition(map, 5L, "Midfielder");
		return map;
	}

	private static void addPosition(Map<Long, Map<String, Object>> positions, Long playerId, String position) {
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("playerId", playerId);
		map.put("position", position);
		positions.put(playerId, map);
	}
}
