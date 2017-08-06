tests:
	@./gradlew clean build test

start:
	@./gradlew clean build && java -jar build/libs/enget-line-bot-0.0.1-SNAPSHOT.jar
