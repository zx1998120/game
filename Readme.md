###Architectured System

Docker environment:

# docker-compose.yml
version: "3.9"
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: game
      MYSQL_USER: game
      MYSQL_PASSWORD: game
    ports: ["3306:3306"]
  redis:
    image: redis:7
    ports: ["6379:6379"]
  rabbitmq:
    image: rabbitmq:3-management
    ports: ["5672:5672", "15672:15672"]
