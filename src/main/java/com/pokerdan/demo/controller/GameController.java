package com.pokerdan.demo.controller;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pokerdan.demo.messaging.Game;
import com.pokerdan.demo.service.GameService;

import io.swagger.annotations.ApiOperation;

// TODO Validate models
@RestController
public class GameController {

    @Autowired
    private GameService gameService;

    @ApiOperation(value = "View all games")
    @GetMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Game>> list() {
        Collection<Game> games = gameService.getAll();
        return new ResponseEntity<>(games, HttpStatus.OK);
    }

    @ApiOperation(value = "Create a game")
    @PostMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> create() {
        Game game = new Game();
        gameService.create(game);
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @ApiOperation(value = "Join a game")
    @PostMapping(value = "/game/{gameId}/join/{playerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> join(@RequestParam("gameId") String gameId, @RequestParam("playerName") String playerName) {
        Optional<Game> game = gameService.join(gameId, playerName);
        if (!game.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Quit a game")
    @DeleteMapping(value = "/game/{gameId}/join/{playerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Game> quit(@RequestParam("gameId") String gameId, @RequestParam("playerName") String playerName) {
        Optional<Game> game = gameService.quit(gameId, playerName);
        if (!game.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(game.get(), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete a game")
    @DeleteMapping(value = "/game", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> delete(String gameId) {
        boolean deleted = gameService.delete(gameId);
        if (!deleted) {
            return new ResponseEntity<>(gameId, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(gameId, HttpStatus.OK);
    }

}
