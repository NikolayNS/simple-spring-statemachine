CREATE SEQUENCE IF NOT EXISTS hibernate_sequence;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TYPE task_state AS ENUM (
    'IDLE', 'FIRST_ACTION_COMPLETED', 'SECOND_ACTION_COMPLETED', 'THIRD_ACTION_COMPLETED', 'FOURTH_ACTION_COMPLETED', 'FIFTH_ACTION_COMPLETED', 'SUCCESS', 'ERROR');

CREATE TABLE IF NOT EXISTS task
(
    id                        uuid      default uuid_generate_v4() not null
        constraint task_pkey
            primary key,
    task_state                task_state                           not null,
    error_message             varchar,
    created_at                timestamp default now()              not null,
    updated_at                timestamp default now()              not null
);