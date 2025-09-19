// mq/RewardListener.java
package com.example.game.mq;
import com.example.game.service.TreasureService; import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Component
public class RewardListener {
  private final TreasureService svc;
  public RewardListener(TreasureService s){ this.svc=s; }

  @RabbitListener(queues = TreasureService.QUEUE_REWARD)
  public void onMessage(String body){
    // body: "playerId:itemCode:count"
    var parts = body.split(":");
    Long pid = Long.parseLong(parts[0]);
    String item = parts[1];
    int cnt = Integer.parseInt(parts[2]);
    svc.grantReward(pid, item, cnt);
  }
}