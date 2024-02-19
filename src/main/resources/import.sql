--DDL

Create database if not exists Media


create table if not exists t_expert
(
    id     bigint not null
    primary key,
    status int    null,
    car_id bigint null
);

create table if not exists t_expert_check_points
(
    id                  bigint       not null
    primary key,
    has_additional_data bit          null,
    is_active           bit          null,
    local_date_time     datetime(6)  null,
    question_id         varchar(255) null,
    expert_id           bigint       null,
    constraint FK5p14ku76njbgdijf725y7472x
    foreign key (expert_id) references media.t_expert (id)
    );

create table if not exists t_expert_check_points_photo_info_list
(
    expert_check_point_id bigint not null,
    photo_info_list_id    bigint not null,
    constraint UK_8m3c7gsqvfl071c9gpq8iek4p
    unique (photo_info_list_id),
    constraint FK9gu8j1dbxv3hwa4975chc0vpf
    foreign key (photo_info_list_id) references media.t_photo (id),
    constraint FKjyqdkb5eaeibl5vddjwkrlaw5
    foreign key (expert_check_point_id) references media.t_expert_check_points (id)
    );

create table if not exists t_expert_check_points_seq
(
    next_val bigint null
);

create table if not exists t_expert_expert_check_point_list
(
    expert_id                  bigint not null,
    expert_check_point_list_id bigint not null,
    constraint UK_j75ei9w35gco2pypwfa6km7p2
    unique (expert_check_point_list_id),
    constraint FK4yms8uydu6g8esfxvhuekx9fb
    foreign key (expert_id) references media.t_expert (id),
    constraint FKcwifejn20tkx632h0lw78rvp0
    foreign key (expert_check_point_list_id) references media.t_expert_check_points (id)
    );

create table if not exists t_expert_seq
(
    next_val bigint null
);

create table if not exists t_photo
(
    id                    bigint       not null
    primary key,
    description           varchar(255) null,
    photourl              varchar(255) null,
    expert_check_point_id bigint       null,
    constraint FK96nf47c884asknsch3e8s5998
    foreign key (expert_check_point_id) references media.t_expert_check_points (id)
    );

create table if not exists t_photo_seq
(
    next_val bigint null
);



--DML

INSERT INTO Media.t_expert (id, status, car_id) VALUES (1, 0, 5676);
INSERT INTO Media.t_expert (id, status, car_id) VALUES (2, 1, 5676);
INSERT INTO Media.t_expert (id, status, car_id) VALUES (3, 0, 8767);

INSERT INTO Media.t_expert_check_points (id, has_additional_data, is_active, local_date_time, question_id, expert_id) VALUES (1, true, true, '2024-02-19 13:01:49.000000', '12', 1);
INSERT INTO Media.t_expert_check_points (id, has_additional_data, is_active, local_date_time, question_id, expert_id) VALUES (2, false, true, '2024-02-19 13:02:04.000000', '12', 3);

INSERT INTO Media.t_photo (id, description, photourl, expert_check_point_id) VALUES (1, 'tampon çizik.', 'https://photo1', 1);
INSERT INTO Media.t_photo (id, description, photourl, expert_check_point_id) VALUES (2, 'çamurluk yırtılmış.', 'https://photo2', 1);
INSERT INTO Media.t_photo (id, description, photourl, expert_check_point_id) VALUES (3, 'çakmaklık bozuk.', 'https://photo3', 1);
