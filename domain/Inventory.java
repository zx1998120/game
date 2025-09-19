package com.example.game.domain;
import jakarta.persistence.*;
import lombok.*;
@Entity @Getter @Setter
@Table(uniqueConstraints=@UniqueConstraint(columnNames={"player_id","item_code"}))
public class Inventory {
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY) Long id;
  @Column(nullable=false) Long playerId;
  @Column(nullable=false) String itemCode;
  @Column(nullable=false) Integer qty;
}
