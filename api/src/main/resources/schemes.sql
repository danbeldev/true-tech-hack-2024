CREATE TABLE venues
(
    id int generated always as identity,
    name varchar(64) not null,
    longitude real not null,
    latitude real not null,

    CONSTRAINT PK__venues__key PRIMARY KEY(id)
);

CREATE TABLE event_status
(
    id smallint generated always as identity,
    name varchar(48) unique not null,

    CONSTRAINT PK__event_status__key PRIMARY KEY(id)
);

INSERT INTO event_status(name) VALUES ('ACTUAL');

CREATE TABLE events
(
    id int generated always as identity,
    name varchar(64) not null,
    description varchar(1080) not null,
    status_id smallint not null,
    venue_id int not null,

    CONSTRAINT PK__events__key PRIMARY KEY(id),
    CONSTRAINT FK__events__status FOREIGN KEY(status_id) REFERENCES event_status(id),
    CONSTRAINT FL__events__venue FOREIGN KEY(venue_id) REFERENCES venues(id)
);

CREATE TABLE categories(
    id int generated always as identity,
    name varchar(48) unique not null,

    CONSTRAINT PK__categories__key PRIMARY KEY(id)
);

CREATE TABLE events_categories
(
    event_id int,
    category_id int,

    CONSTRAINT PK__events_categories__key PRIMARY KEY(event_id, category_id),
    CONSTRAINT FK__events_categories__event FOREIGN KEY(event_id) REFERENCES events(id),
    CONSTRAINT FK__events_categories__category FOREIGN KEY(category_id) REFERENCES categories(id)
);

CREATE TABLE event_schedules
(
    id int generated always as identity,
    event_id int not null,
    date_time timestamp not null,

    CONSTRAINT PK__event_schedules__key PRIMARY KEY(id),
    CONSTRAINT FK__event_schedules__event FOREIGN KEY(event_id) REFERENCES events(id)
);

CREATE TABLE persons
(
    id int generated always as identity,
    first_name varchar(48) not null,
    last_name varchar(48) not null,
    middle_name varchar(48) default null,
    description varchar(256) default null,

    CONSTRAINT PK__persons__key PRIMARY KEY(id)
);

CREATE TABLE posts
(
    id smallint generated always as identity,
    name varchar(48) unique not null,

    CONSTRAINT PK__posts__key PRIMARY KEY(id)
);


CREATE TABLE events_persons
(
    event_id int,
    person_id int,
    post_id smallint,

    CONSTRAINT PK__events_persons__key PRIMARY KEY(event_id, person_id, post_id),
    CONSTRAINT FK__events_persons__event FOREIGN KEY(event_id) REFERENCES events(id),
    CONSTRAINT FK__events_persons__person FOREIGN KEY(person_id) REFERENCES persons(id),
    CONSTRAINT FK__events_persons__post FOREIGN KEY(post_id) REFERENCES posts(id)
);


CREATE TABLE venue_feature_types
(
    id smallint generated always as identity,
    name varchar(32) unique not null,

    CONSTRAINT PK__features__key PRIMARY KEY(id)
);

INSERT INTO venue_feature_types(name) VALUES ('WHEELCHAIR'), ('BLIND');

CREATE TABLE venues_features(
    venue_id int,
    type_id smallint,

    CONSTRAINT PK__venues_features__key PRIMARY KEY(venue_id, type_id),
    CONSTRAINT FK__venues_features__venue FOREIGN KEY(venue_id) REFERENCES venues(id),
    CONSTRAINT FK__venues_features__type FOREIGN KEY(type_id) REFERENCES venue_feature_types(id)
);

CREATE TABLE files
(
    id int generated always as identity,
    name varchar(48) unique not null,

    CONSTRAINT PK__images__key PRIMARY KEY(id)
);

CREATE TABLE events_images
(
    event_id int,
    image_id int,

    CONSTRAINT PK__events_images__key PRIMARY KEY(event_id, image_id),
    CONSTRAINT FK__events_images__event FOREIGN KEY(event_id) REFERENCES events(id),
    CONSTRAINT FK__events_images__image FOREIGN KEY(image_id) REFERENCES files(id)
);

ALTER TABLE persons
    ADD COLUMN photo_id int default null;

ALTER TABLE persons
    ADD CONSTRAINT FK__persons__photo FOREIGN KEY(photo_id) REFERENCES files(id)