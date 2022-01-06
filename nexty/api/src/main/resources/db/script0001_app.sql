
CREATE SCHEMA application;

--------------------------------------------------------------------------------

create table application.application
(
    id         serial primary key,
    name       varchar(100)   not null,
    url        varchar(250)   not null,
    created_at timestamptz(0) not null,
    updated_at timestamptz(0)
);

create unique index on application.application (name);

create index on application.application (name);

create index on application.application (created_at);

create index on application.application (updated_at);

--------------------------------------------------------------------------------
--------------------------------------------------------------------------------

INSERT INTO application.application (name, url, created_at, updated_at)
VALUES ('NEXTY', 'http://nexty.demandante.com.br', now(), now());
