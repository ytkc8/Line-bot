SET @@auto_increment_increment = 1;

INSERT INTO word (
 source_word,
 english_word
) VALUES
 ('いぬ', 'dog'),
 ('イヌ', 'dog'),
 ('犬', 'dog'),
 ('ねこ', 'cat'),
 ('ネコ', 'cat'),
 ('猫', 'cat');
