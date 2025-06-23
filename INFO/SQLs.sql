CREATE TABLE IF NOT EXISTS contacts(
        id int GENERATED ALWAYS AS IDENTITY,
        name varchar(32),
        surname varchar(32),
        phone varchar(64)
);