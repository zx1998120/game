###Architectured System
	•	Unity Client (C#) → sends requests (login, mine treasure)
	•	Spring Boot Backend (Java) → handles auth, gameplay API, reward distribution
	•	MySQL → stores player & inventory
	•	Redis → distributed lock
	•	RabbitMQ → async reward processing
