import random
import time
import psycopg2
import datetime
import logging 

logging.basicConfig(level=logging.INFO)



class TransactionsProducer:

    def __init__(self, db_name, user, host, password):
        self.db_name = db_name
        self.user = user
        self.host = host
        self.password = password

    def connect_db(self):
  
        return psycopg2.connect(
            dbname=self.db_name,
            user=self.user,
            host=self.host,
            password=self.password
        )

    def produce(self):

        while True:
            customer_id = random.randint(1, 100)
            amount = round(random.uniform(1, 1000), 2)  # Ensure a monetary format
            order_id = f"ORD{random.randint(100, 999)}"
            transaction_time = datetime.datetime.now()

            conn = self.connect_db()
            if conn is None:
                logging.info("failed db connection, retry after 60 s")
                time.sleep(60)
                continue      

            try:
                cur = conn.cursor()
                cur.execute(
                    "INSERT INTO transactions (customer_id, amount, order_id, transaction_time) VALUES (%s, %s, %s, %s)",
                    (customer_id, amount, order_id, transaction_time)
                )

                conn.commit()
                logging.info(f"Inserted: {customer_id}, {amount}, {order_id}, {transaction_time}")

            except Exception as e:
                logging.error(f"Database error: {e}")
            finally:
                cur.close()
                conn.close()

            time.sleep(60) 
if __name__ == "__main__":
    try:
     producer = TransactionsProducer(db_name="loyalty_db", user="admin", host="loyalty_postgres",password="mysecurepassword")
     producer.produce()
    except Exception as e:
     print(f"Error occurred: {e}")