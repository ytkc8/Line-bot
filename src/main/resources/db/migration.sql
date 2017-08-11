SET @@auto_increment_increment = 1;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS word;

CREATE TABLE word (
  id            BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  source_word   VARCHAR(100),
  english_word  VARCHAR(100)
);