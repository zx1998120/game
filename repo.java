package com.example.game.repo;
import com.example.game.domain.*; import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface PlayerRepo extends JpaRepository<Player, Long> {
  Optional<Player> findByUsername(String u);
}
public interface InventoryRepo extends JpaRepository<Inventory, Long> {
  Optional<Inventory> findByPlayerIdAndItemCode(Long pid, String item);
}