start transaction;

create schema "player";

create table player.channel(
    id serial primary key,
    name varchar(2048),
    description text,
    eTag varchar(256),
    youtubeId varchar(256) unique not null,
    uploadedId varchar(256) unique not null,
    uploadedETag varchar(256)
);

create table player.video(
    id serial primary key,
    name varchar(2048),
    description text,
    date timestamp,
    duration int,
    likes int,
    dislikes int,
    views int,
    eTag varchar(256),
    youtubeId varchar(256) unique not null,
    channel_id int references player.channel(id) on delete cascade
);

create table player.tag(
    id serial primary key,
    value varchar(512) not null unique
);

create table player.video_has_tag(
    video_id int references player.video(id),
    tag_id int references player.tag(id)
);

commit;

