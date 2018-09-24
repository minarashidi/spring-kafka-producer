# spring-kafka-producer
In this poc, I show how we can produce messages and publish them to Apache Kafka

First you need to run Zookeeper and Kafka.
Change to bin directory of Kafka and run zookeeper:

./zookeeper-server-start.sh ../config/zookeeper.properties

then we need to run Kafka:

./kafka-server-start.sh ../config/server.properties



Now you are able to run producer application which publishes a message to our topic named event.


