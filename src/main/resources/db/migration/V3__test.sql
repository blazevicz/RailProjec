CREATE TABLE owner
(
    owner_id SERIAL PRIMARY KEY,
    pet_id   INTEGER
);

CREATE TABLE pet
(
    pet_id SERIAL PRIMARY KEY

);

CREATE TABLE toy
(
    toy_id SERIAL PRIMARY KEY,
    toy    INTEGER,
    pet_id INTEGER
);

ALTER TABLE owner
    ADD CONSTRAINT FK_pet FOREIGN KEY (pet_id) REFERENCES pet (pet_id);
ALTER TABLE toy
    ADD CONSTRAINT FK_pet FOREIGN KEY (pet_id) REFERENCES pet (pet_id);
