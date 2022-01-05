
CREATE SCHEMA security;

--------------------------------------------------------------------------------

create table security.user_status
(
    id   integer primary key,
    name varchar(255) not null
);

create unique index on security.user_status (name);

--------------------------------------------------------------------------------

create table security.role_group_status
(
    id   integer primary key,
    name varchar(255) not null
);

create unique index on security.role_group_status (name);

--------------------------------------------------------------------------------

create table security.user_group_status
(
    id   integer primary key,
    name varchar(255) not null
);

create unique index on security.user_group_status (name);

--------------------------------------------------------------------------------

create table security.user
(
    id                serial primary key,
    name              varchar(255)   not null,
    email             varchar(255)   not null,
    email_verified_at timestamptz(0),
    password          varchar(255),
    remember_token    varchar(100),
    created_at        timestamptz(0) not null,
    updated_at        timestamptz(0),
    demandante_id         integer        not null references dev.demandante,
    status_id         integer        not null references security.user_status
);

create unique index on security.user (name);

create unique index on security.user (email);

create unique index on security.user (person_id);

comment on table security.user is 'Armazena os usuários da aplicação';
comment on column security.user.email is 'Armazena o nome da credencial. O nome é único (independente do status)';
comment on column security.user.name is 'Armazena o email da credencial. O email é único (independente do status)';

--------------------------------------------------------------------------------

create table security.password_resets
(
    id         serial primary key,
    email      varchar(255)   not null,
    token      varchar(255)   not null,
    created_at timestamptz(0) not null,
    user_id    integer        not null references security.user
);

create index on security.password_resets (email);

comment on table security.password_resets is 'Armazena os tokens que serão utilizados na recuperação de senha';

--------------------------------------------------------------------------------

create table security.password_historic
(
    id		   serial primary key,
    email      varchar(255)   not null,
    password   varchar(255)   not null,
    created_at timestamptz(0) not null,
    user_id    integer        not null references security.user
);

create index on security.password_historic (email);

comment on table security.password_historic is 'Armazena as 3 últimas senhas de cada usuário para para validação ao recuperar senha';

--------------------------------------------------------------------------------

create table security.role_group
(
    id             serial primary key,
    name           varchar(100) not null,
    description    varchar(250) not null,
    application_id integer      not null references application.application,
    status_id      integer      not null references security.role_group_status
);

create unique index on security.role_group (application_id, name) where status_id = 1;

create index on security.role_group (application_id, name);

--------------------------------------------------------------------------------

create table security.role
(
    id       serial primary key,
    code     varchar(50)  not null,
    name     varchar(100) not null,
    group_id integer      not null references security.role_group
);

create unique index on security.role (code);

create unique index on security.role (group_id, name);

create index on security.role (code);

create index on security.role (group_id, name);

--------------------------------------------------------------------------------

create table security.user_group
(
    id             serial primary key,
    name           varchar(100)   not null,
    description    varchar(250)   not null,
    application_id integer        not null references application.application,
    created_at     timestamptz(0) not null,
    updated_at     timestamptz(0),
    status_id      integer        not null references security.user_group_status
);

create unique index on security.user_group (application_id, name) where status_id = 1;

create index on security.user_group (application_id, name);

create index on security.user_group (application_id, created_at);

create index on security.user_group (application_id, updated_at);

--------------------------------------------------------------------------------

create table security.user_group_role
(
    id            serial primary key,
    user_group_id integer not null references security.user_group,
    role_id       integer not null references security.role
);

create unique index on security.user_group_role (user_group_id, role_id);

--------------------------------------------------------------------------------

create table security.permission
(
    id             serial primary key,
    application_id integer        not null references application.application,
    user_id        integer        not null references security."user",
    user_group_id  integer        not null references security.user_group,
    created_at     timestamptz(0) not null,
    updated_at     timestamptz(0),
    company_id     integer references society.person
);

create unique index on security.permission (user_id, user_group_id);

create index on security.permission (user_id);

------------------------------------------------------------------------------------------------------------------------

insert into security.user_status (id, name)
VALUES (1, 'Ativo'),
       (2, 'Inativo');

------------------------------------------------------------------------------------------------------------------------

insert into security.user_group_status (id, name)
VALUES (1, 'Ativo'),
       (2, 'Inativo');

------------------------------------------------------------------------------------------------------------------------

insert into security.role_group_status (id, name)
VALUES (1, 'Ativo'),
       (2, 'Inativo');

------------------------------------------------------------------------------------------------------------------------

insert into security.role_group (name, description, application_id, status_id)
VALUES ('USER', 'Permissão de usuários', 1, 1);

insert into security.role (code, name, group_id)
VALUES ('USER_CREATE', 'Cadastro', 1),
       ('USER_UPDATE', 'Alteração', 1),
       ('USER_SELF_UPDATE', 'Meus Dados', 1),
       ('USER_LIST', 'Lista', 1),
       ('USER_VIEW', 'Visualização', 1),
       ('USER_DEACTIVATE', 'Desativação', 1),
       ('USER_ACTIVATE', 'Ativação', 1);


------------------------------------------------------------------------------------------------------------------------

insert into security.user_group (name, description, application_id, created_at, status_id)
select 'Root', 'Super Usuário', application.id, now(), 1
from application.application;

------------------------------------------------------------------------------------------------------------------------

insert into society.person (identifier, name, email, created_at, updated_at, type_id, identifier_type_id)
values ('04209730106', 'Maria Carolina Santana Ribeiro', 'mmariasribeiro@gmail.com', now(), null, 1, 1);

INSERT INTO security.user (name, email, email_verified_at, password, remember_token, created_at, updated_at, person_id,
                           status_id)
VALUES ('04209730106',
        'mmariasribeiro@gmail.com',
        null,
           -- senha é 123456789
        '$2a$10$w8dlnUHhYa4BR9kIvIeag.w.U/8PYDzRFBhUY59u3y5j6b3EuZcgy',
        null,
        '2022-01-04 19:36:39',
        '2022-01-04 19:36:39',
        (select id from society.person where identifier = '04209730106'),
        1);

-----------------------------------------------------------------------------------------------------------------------

-- perfil de root para todos os sistemas
insert into security.permission (application_id, user_id, user_group_id, created_at, company_id)
select user_group.application_id                                            as application_id,
       (select id from security.user where email = 'mmariasribeiro@gmail.com') as user_id,
       user_group.id                                                        as user_group_id,
       now()                                                                as created_at,
       null                                                                 as company_id
from security.user_group
where name = 'Root';

-----------------------------------------------------------------------------------------------------------------------

create table security.historic_user
(
    id            serial primary key,
    demandante_id     integer not null references dev.demandante,
    user_id    integer not null references security.user,
    action        varchar(50)  not null,
    action_at     timestamptz(0) not null,
    justification varchar(2000),
    field         varchar(2000),
    before        varchar(2000),
    later         varchar(2000)
);

-----------------------------------------------------------------------------------------------------------------------

-- perfil de root para todos os sistemas
insert into security.user_group_role (user_group_id, role_id)
select 1, id
from security.role;
