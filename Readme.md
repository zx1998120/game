# README – Multiplayer SLG Game Skeleton


- Unity Client (C#) → sends requests (login, mine treasure)
- Spring Boot Backend (Java) → handles auth, gameplay API, reward distribution
- MySQL → stores player & inventory
- Redis → distributed lock
- RabbitMQ → async reward processing

## Overview

This is a demo architecture for a large-scale multiplayer SLG mobile game.
It demonstrates Unity client + Spring Boot backend + MySQL + Redis + RabbitMQ.

### Key features:
- Stateless auth with JWT
- Mining gameplay logic with inventory sync
- Distributed locks (Redis) to prevent double-mining
- Asynchronous reward processing (RabbitMQ)
- Persistent storage (MySQL)
- Client implemented in Unity (C#)


## Game Pictures

<img width="1080" height="473" alt="image" src="https://github.com/user-attachments/assets/24412bee-5f15-48cb-b2db-61a44ac26f75" />
<img width="1080" height="476" alt="image" src="https://github.com/user-attachments/assets/26a0a6de-7572-453c-b548-ed8d59f6f372" />
<img width="1080" height="475" alt="image" src="https://github.com/user-attachments/assets/69fba350-398d-4a45-b9a8-f3bf169fbac0" />



