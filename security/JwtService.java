// security/JwtService.java
package com.example.game.security;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value; import org.springframework.stereotype.Component;
import java.security.Key; import java.util.Date;
@Component
public class JwtService {
  private final Key key; private final long ttlMs;
  public JwtService(@Value("${jwt.secret}") String secret,
                    @Value("${jwt.expireHours}") int hours) {
    this.key = Keys.hmacShaKeyFor(secret.getBytes());
    this.ttlMs = hours * 3600_000L;
  }
  public String issue(String username) {
    long now=System.currentTimeMillis();
    return Jwts.builder().setSubject(username)
      .setIssuedAt(new Date(now)).setExpiration(new Date(now+ttlMs))
      .signWith(key, SignatureAlgorithm.HS256).compact();
  }
  public String subject(String token){
    return Jwts.parserBuilder().setSigningKey(key).build()
      .parseClaimsJws(token).getBody().getSubject();
  }
}