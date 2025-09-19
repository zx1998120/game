// controller/AuthController.java
package com.example.game.controller;
import com.example.game.dto.*; import com.example.game.domain.Player;
import com.example.game.repo.PlayerRepo; import com.example.game.security.JwtService;
import org.springframework.http.*; import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCrypt;
@RestController @RequestMapping("/api/auth")
public class AuthController {
  private final PlayerRepo players; private final JwtService jwt;
  public AuthController(PlayerRepo p, JwtService j){ this.players=p; this.jwt=j; }

  @PostMapping("/login")
  public ResponseEntity<LoginResp> login(@RequestBody LoginReq req){
    var p = players.findByUsername(req.username()).orElse(null);
    if (p == null || !BCrypt.checkpw(req.password(), p.getPasswordHash()))
      return ResponseEntity.status(401).build();
    return ResponseEntity.ok(new LoginResp(jwt.issue(p.getUsername())));
  }
}