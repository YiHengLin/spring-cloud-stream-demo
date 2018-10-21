# spring-cloud-stream-demo
Spring cloud stream with binder RabbitMQ 

1. With RabbitMQ server running on your machine.
2. Send a POST request with your favorite player name and team he/she beloongs to:

`curl -X POST -H "Content-Type:application/json" -d "{\"team\":\"Warrior\", \"name\":\"Curry\"}"  localhost:8080/player`

3. Producer will sned message through output channel to RabbitMQ exchange "__player__", whcich is binded to queue "__player.NBA__".
Consumer reveives message through input channel and prints the message.
