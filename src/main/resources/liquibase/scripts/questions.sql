-- liquibase formatted sql
-- changeset antonov333:1
CREATE table questions(
                        id SERIAL PRIMARY KEY,
                        topic TEXT,
                        question TEXT,
                        answer TEXT,
                        source TEXT)