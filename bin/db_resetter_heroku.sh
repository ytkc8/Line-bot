#!/bin/sh

mysql -h us-cdbr-iron-east-05.cleardb.net -ubd08cd549b9dd9 -p9b78bda9 heroku_cc10aea301daf49 < ./src/main/resources/db/migration.sql
mysql -h us-cdbr-iron-east-05.cleardb.net -ubd08cd549b9dd9 -p9b78bda9 heroku_cc10aea301daf49 < ./src/main/resources/db/seed.sql
