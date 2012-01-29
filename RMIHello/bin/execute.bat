javac HelloService.java
javac Hello.java
rmic HelloService
start rmiregistry
start java -Djava.security.policy=java.policy HelloService
javac HelloClient.java
java HelloClient
pause