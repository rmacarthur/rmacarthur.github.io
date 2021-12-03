BEGIN TRANSACTION;

DROP TABLE if exists users_rewards;
DROP TABLE if exists rewards;
DROP TABLE if exists reading_activities;
DROP TABLE if exists books;
DROP TABLE IF EXISTS users;
DROP TABLE if exists families;
DROP SEQUENCE IF EXISTS seq_user_id;

create table families (
        family_id serial,
        family_name varchar(75),
        
        constraint pk_families_family primary key (family_id)
);

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	family_id int,
	is_parent boolean DEFAULT false,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,	
	
	CONSTRAINT PK_user PRIMARY KEY (user_id),
	CONSTRAINT fk_user_family FOREIGN KEY (family_id) REFERENCES families (family_id)
	
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

COMMIT TRANSACTION;


BEGIN TRANSACTION;

create table books (
        book_id serial PRIMARY KEY,
        isbn varchar(17),
        title varchar(75),
        author varchar(75),
        description varchar(300)
);

create table reading_activities (
        reading_activity_id serial,
        book_id int not null,
        reader_id int not null,
        date_entered date DEFAULT CURRENT_DATE,
        time_in_mins int,
        reading_type varchar(50),
        book_is_completed boolean DEFAULT false,
        notes varchar(300),
        
        constraint pk_reading_activity primary key (reading_activity_id),
        constraint fk_reading_activity_book foreign key (book_id) references books (book_id),
        constraint fk_reading_activity_reader foreign key (reader_id) references users (user_id)
);

create table rewards (
        reward_id serial,
        family_id int,
        minutes_required int,
        start_date date,
        end_date date,
        max_recipients int,
        description varchar(300),
        
        constraint pk_rewards_reward primary key (reward_id),
        constraint fk_reward_family foreign key (family_id) references families (family_id)
        
);

create table users_rewards (
        user_id int,
        reward_id int,
        PRIMARY KEY (user_id, reward_id),
        
        constraint fk_reward_user foreign key (user_id) references users (user_id),
        constraint fk_reward_reward foreign key (reward_id) references rewards (reward_id)
);

-- Dummy data for testing purposes --
INSERT INTO families (family_id, family_name) VALUES (1, 'Smith');
INSERT INTO families (family_id, family_name) VALUES (2, 'Johnson');
INSERT INTO families (family_id, family_name) VALUES (3, 'Rench');

INSERT INTO users (username, family_id, is_parent, password_hash, role) VALUES ('AlanRench', 3, true, '$2a$10$JUgttMtspuaSL.7UyHBgt.hNAQb8xPDBbXTmIYAzycwtO6tiMdmgm', 'ROLE_USER');
INSERT INTO users (username, family_id, is_parent, password_hash, role) VALUES ('MonkeyRench', 3, false, '$2a$10$GHXeoNSAfODZrqR1kL5vvuzNdJgjzzaWDJejwrl616F.zJc6cwxtK', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('LugRench', '$2a$10$Kc6YNW5xpYE62pmz6Qkh9Oa/ywkaJv/qsPd47ZOsFDGiqN4FUS9km', 'ROLE_USER');
INSERT INTO users (username, password_hash, role) VALUES ('StubbyRench', '$2a$10$9wKu9sif2g4mkDGH7uFDUeLoPGdy6.WUjh.noC64/6KIIesNCFulq', 'ROLE_USER');
INSERT INTO users (username, family_id, is_parent, password_hash, role) VALUES ('JohnSmith', 1, false, '$2a$10$p06dCDF11rcLWUg22Mhoiutl9zPGgaERm88DUgm40EispcxT08zem', 'ROLE_USER');
INSERT INTO users (username, family_id, is_parent, password_hash, role) VALUES ('AgentSmith', 1, true, '$2a$10$ZWAWWiTL69j/34LR.lkiPOfqOZ1mbv45YNPBXcLrfCHlHyBWoSXLu', 'ROLE_USER');
INSERT INTO users (username, family_id, is_parent, password_hash, role) VALUES ('BigJohnson', 2, true, '$2a$10$nApviOr61cbyYB4hsuNz7.gEWu0bAyezad8TIC9yA5OjnU9a3UioW', 'ROLE_USER');
INSERT INTO users (username, family_id, password_hash, role) VALUES ('LilJohnson', 2, '$2a$10$0Ht9gk9W3cKtBd2VhFkcWekymn/EeW8PSrt4pv.VEjevjSs42MXZ2', 'ROLE_USER');

INSERT INTO books (isbn, title, author, description) VALUES (9780356500157, 'Kafka by the Shore', 'Haruki Murakami', 'Description stuff here ...');
INSERT INTO books (isbn, title, author, description) VALUES (9781590302484, 'The Book of Five Rings', 'Miyamoto Musashi', 'Description stuff here ...');
INSERT INTO books (isbn, title, author, description) VALUES (9780195014761, 'The Art of War', 'Sun Tzu', 'Description things ...');
INSERT INTO books (isbn, title, author, description) VALUES (9780553296129, 'Heir to the Empire', 'Timothy Zahn', 'You can tell a great deal about a species from their art.');

INSERT INTO reading_activities (book_id, reader_id, time_in_mins, reading_type, notes) VALUES (4, 4, 45, 'Audiobook', 'Mara Jade seems like a bad ass!');
INSERT INTO reading_activities (book_id, reader_id, time_in_mins, reading_type, notes) VALUES (3, 3, 60, 'Secretary Recitation', 'I know Kung-Fu');
INSERT INTO reading_activities (book_id, reader_id, time_in_mins, reading_type, notes) VALUES (3, 4, 40, 'Paperback', '...');
INSERT INTO reading_activities (book_id, reader_id, time_in_mins, reading_type, notes) VALUES (2, 5, 120, 'Paperback', 'A sword fight is like a game of chess ...');

INSERT INTO rewards (minutes_required, start_date, end_date, max_recipients, description) VALUES (300, '2021-09-01', '2021-10-31', 2, 'Individual Read More!');
INSERT INTO rewards (family_id, minutes_required, start_date, end_date, max_recipients, description) VALUES (3, 300, '2021-09-01', '2021-10-31', 2, 'Family Read More!');

COMMIT TRANSACTION;