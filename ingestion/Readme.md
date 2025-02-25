# Project Name

## Description
A brief description of what your project does.

## Prerequisites
- Docker
- Kafka
- PostgreSQL
- Debezium

## Setup Instructions

### 1. Run PostgreSQL:
Ensure PostgreSQL is running and accessible, with the following environment variables:
- **Database**: `loyalty_db`
- **User**: `admin`
- **Password**: `mysecurepassword`

### 2. Set up Kafka:
Start Kafka by following the instructions for your environment.

### 3. Set up Debezium:
1. Configure Debezium to connect to PostgreSQL by editing the configuration file or sending a POST request to Kafka Connect API.

2. Example configuration for Debezium (with the necessary parameters):
   ```json
   {
     "name": "loyalty_cdc",
     "config": {
       "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
       "database.hostname": "loyalty_postgres",
       "database.port": "5432",
       "database.user": "admin",
       "database.password": "mysecurepassword",
       "database.dbname": "loyalty_db",
       "database.server.name": "loyalty_server",
       "table.include.list": "public.loyalty_transactions",
       "plugin.name": "pgoutput",
       "slot.name": "debezium_slot",
       "publication.name": "debezium_publication",
       "database.history.kafka.bootstrap.servers": "kafka:9093",
       "database.history.kafka.topic": "dbhistory.loyalty_db",
       "topic.prefix": "loyalty_points_"
     }
   }
