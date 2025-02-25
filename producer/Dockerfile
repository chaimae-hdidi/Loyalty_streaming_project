# Use a lightweight Python image
FROM python:3.9-slim

# Set the working directory inside the container
WORKDIR /app

# Copy application files 
COPY /TransactionsProducerClass.py /app
COPY /main.py /app

# Install dependencies
RUN pip install psycopg2-binary

# Run the application
CMD ["python3", "TransactionsProducerClass.py"]
