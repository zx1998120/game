// service/TreasureService.java
package com.example.game.service;
import com.example.game.dto.MineReq; import com.example.game.domain.Inventory;
import com.example.game.repo.*; import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.StringRedisTemplate; import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Duration; import java.util.concurrent.TimeUnit;

@Service
public class TreasureService {
  private final InventoryRepo invRepo;
  private final PlayerRepo playerRepo;
  private final StringRedisTemplate redis;
  private final RabbitTemplate mq;
  public static final String QUEUE_REWARD = "reward.queue";

  public TreasureService(InventoryRepo i, PlayerRepo p, StringRedisTemplate r, RabbitTemplate mq){
    this.invRepo=i; this.playerRepo=p; this.redis=r; this.mq=mq;
  }

 
  @Transactional
  public void mine(String username, MineReq req){
    var player = playerRepo.findByUsername(username).orElseThrow();
    String lockKey = "lock:mine:" + player.getId() + ":" + req.itemCode();
    Boolean ok = redis.opsForValue().setIfAbsent(lockKey, "1", Duration.ofSeconds(2));
    if(Boolean.FALSE.equals(ok)) throw new IllegalStateException("busy");

    try {
 
      mq.convertAndSend(QUEUE_REWARD, player.getId() + ":" + req.itemCode() + ":" + req.count());
    } finally {
      redis.delete(lockKey);
    }
  }

  @Transactional
  public void grantReward(Long playerId, String itemCode, int count){
    var inv = invRepo.findByPlayerIdAndItemCode(playerId, itemCode)
      .orElseGet(() -> { var x=new Inventory(); x.setPlayerId(playerId); x.setItemCode(itemCode); x.setQty(0); return x; });
    inv.setQty(inv.getQty() + count);
    invRepo.save(inv);
  }
}