tests:
	@./gradlew clean build test

start:
	@./gradlew clean build && java -jar build/libs/enget-line-bot-0.0.1-SNAPSHOT.jar

reset_db_dev:
	@./bin/db_resetter.sh enget_line_bot

reset_db_test:
	@./bin/db_resetter.sh enget_line_bot_test

reset_db_heroku:
	@./bin/db_resetter_heroku.sh
