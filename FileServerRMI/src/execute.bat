javac FileServer.java
javac FileService.java
rmic FileServer
start rmiregistry
start java -Djava.security.policy=java.policy FileServer
javac FileClient.java
java FileClient
pause