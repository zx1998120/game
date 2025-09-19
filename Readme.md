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
