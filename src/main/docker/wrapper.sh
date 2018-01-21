#!/bin/bash
while ! exec 6<>/dev/tcp/videodata-postgres/5432; do
    echo "Trying to connect to PostgreSQL at 5432..."
    sleep 10
done

java -Djava.security.egd=file:/dev/./urandom -Dspring.profiles.active=container -jar /app.jar
