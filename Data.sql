-- schema.sql
CREATE TABLE IF NOT EXISTS player (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(64) UNIQUE NOT NULL,
  password_hash VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS inventory (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  player_id BIGINT NOT NULL,
  item_code VARCHAR(64) NOT NULL,
  qty INT NOT NULL,
  UNIQUE KEY uk_player_item (player_id, item_code),
  FOREIGN KEY (player_id) REFERENCES player(id)
);

CREATE TABLE IF NOT EXISTS action_log (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  player_id BIGINT NOT NULL,
  action_type VARCHAR(64) NOT NULL,
  payload JSON,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);