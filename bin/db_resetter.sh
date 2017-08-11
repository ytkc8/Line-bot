#!/bin/sh

mysql -u root $1 < ./src/main/resources/db/migration.sql

if [ "$1" == "enget_line_bot" ];then
  mysql -u root $1 < ./src/main/resources/db/seed.sql
fi
