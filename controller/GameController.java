// controller/GameController.java
package com.example.game.controller;
import com.example.game.dto.*; import com.example.game.security.JwtService;
import com.example.game.service.TreasureService;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/game")
public class GameController {
  private final JwtService jwt; private final TreasureService treasure;
  public GameController(JwtService j, TreasureService t){ this.jwt=j; this.treasure=t; }

  @PostMapping("/mine")
  public ResponseEntity<ActionAck> mine(@RequestHeader("Authorization") String auth,
                                        @RequestBody MineReq req){
    String token = auth.replace("Bearer ", "");
    String username = jwt.subject(token);
    treasure.mine(username, req);
    return ResponseEntity.ok(new ActionAck("queued"));
  }
}