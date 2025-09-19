package com.example.game.domain;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter
public class Player {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
  @Column(unique=true, nullable=false) String username;
  @Column(nullable=false) String passwordHash;
}
