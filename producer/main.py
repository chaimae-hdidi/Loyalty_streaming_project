from TransactionProducerClass import TransactionsProducer




if __name__ == "__main__":
    try:
     producer = TransactionsProducer(db_name="loyalty_db", user="admin", host="loyalty_postgres",password="mysecurepassword")
     producer.produce()
    except Exception as e:
     print(f"Error occurred: {e}")