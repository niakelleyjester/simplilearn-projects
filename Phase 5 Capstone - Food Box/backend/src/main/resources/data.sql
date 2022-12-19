/* Filling the cuisines table */
INSERT INTO cuisines (cuisine_name) VALUES ('American');
INSERT INTO cuisines (cuisine_name) VALUES ('Indian');
INSERT INTO cuisines (cuisine_name) VALUES ('British');
INSERT INTO cuisines (cuisine_name) VALUES ('Japanese');
INSERT INTO cuisines (cuisine_name) VALUES ('Chinese');
INSERT INTO cuisines (cuisine_name) VALUES ('Mexican');
INSERT INTO cuisines (cuisine_name) VALUES ('French');
INSERT INTO cuisines (cuisine_name) VALUES ('Persian');
INSERT INTO cuisines (cuisine_name) VALUES ('Italian');
INSERT INTO cuisines (cuisine_name) VALUES ('Spanish');
COMMIT;


/* Filling the tags table */
INSERT INTO tags (tagName) VALUES ('all');
INSERT INTO tags (tagName) VALUES ('pasta');
INSERT INTO tags (tagName) VALUES ('vegetarian');
INSERT INTO tags (tagName) VALUES ('seafood');
INSERT INTO tags (tagName) VALUES ('meat');
INSERT INTO tags (tagName) VALUES ('rice');
INSERT INTO tags (tagName) VALUES ('chicken');
INSERT INTO tags (tagName) VALUES ('bread');
INSERT INTO tags (tagName) VALUES ('sandwich');
INSERT INTO tags (tagName) VALUES ('salad');
INSERT INTO tags (tagName) VALUES ('beef');
INSERT INTO tags (tagName) VALUES ('noodles');
INSERT INTO tags (tagName) VALUES ('soup');
INSERT INTO tags (tagName) VALUES ('stew');
INSERT INTO tags (tagName) VALUES ('side');
COMMIT;

/*Filling userRoles table*/
INSERT INTO userRoles (roletype) values ('admin');
INSERT INTO userRoles (roletype) values ('customer');
INSERT INTO userRoles (roletype) values ('guest');
COMMIT;


/*Filling the products table with JSON data*/