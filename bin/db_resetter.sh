#!/bin/sh

mysql -u root $1 < ./src/main/resources/db/migration.sql
mysql -u root $1 < ./src/main/resources/db/seed.sql
