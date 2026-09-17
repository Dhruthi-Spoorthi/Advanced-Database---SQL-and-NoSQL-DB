# MetroRide Hybrid Database

MetroRide is a small ride-sharing database project using PostgreSQL for transactional data and MongoDB for flexible vehicle and review data.

## Stack

- Java 21
- Spring Boot
- PostgreSQL
- MongoDB
- Maven
- Docker Compose

## Database split

PostgreSQL stores customers, drivers, rides and payments. These records use relationships, constraints and transactions.

MongoDB stores vehicle documents and ride reviews. The document structure allows vehicle features, service areas and review tags to vary without changing a relational schema.

## Run

Requirements:

- Java 21
- Maven
- Docker Desktop

Start the databases:

```bash
docker compose up -d
```

Build and run the application:

```bash
mvn clean spring-boot:run
```

The API starts on `http://localhost:8080`.

## Useful endpoints

```text
GET    /api/health
GET    /api/rides
GET    /api/rides/1
PATCH  /api/rides/1/status?value=COMPLETED
DELETE /api/rides/120
GET    /api/analytics/driver-revenue
GET    /api/drivers/1/reviews
```

## Database scripts

PostgreSQL:

- `database/postgres/schema.sql`
- `database/postgres/data.sql`
- `database/postgres/queries.sql`

MongoDB:

- `database/mongodb/init.js`
- `database/mongodb/queries.js`

The seed scripts create 120 records for each SQL table and 120 documents in each MongoDB collection.

## Design

The SQL side keeps customer, driver, ride and payment data separate so that the relationships and payment rules can be enforced with foreign keys and constraints.

MongoDB is used for vehicles and reviews because their attributes are more flexible. Indexes are included for the common availability, service-area, driver and ride lookups.

## Project structure

```text
src/
database/
  postgres/
  mongodb/
diagrams/
docker-compose.yml
pom.xml
```
