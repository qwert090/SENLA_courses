CREATE SCHEMA cd;

CREATE TABLE cd.facilities
(
    facility_id integer NOT null, 
    name character varying(100) NOT null, 
    member_cost numeric NOT null, 
    guest_cost numeric NOT null, 
    initialoutlay numeric NOT null, 
    monthly_maintenance numeric NOT null, 
    CONSTRAINT facilities_pk PRIMARY KEY (facility_id)
);

INSERT INTO cd.facilities (facility_id, name, member_cost, guest_cost, initialoutlay, monthly_maintenance)
VALUES (0, 'Tennis Court 1', 5, 25, 10000, 200),
(1,'Tennis Court 2', 5, 25, 8000, 200),
(2,'Badminton Court', 0, 15.5, 4000, 50),
(3,'Table Tennis', 0, 5, 320, 10),
(4,'Massage Room 1', 35, 80, 4000, 3000),
(5,'Massage Room 2', 35, 80, 4000, 3000);

CREATE TABLE cd.members
(
    id integer NOT null, 
    surname character varying(200) NOT null, 
    firstname character varying(200) NOT null,
  	recommended_by integer,
    joindate timestamp NOT null,
    CONSTRAINT members_pk PRIMARY KEY (id)
);

INSERT INTO cd.members (id, surname, firstname, recommended_by, joindate)
VALUES (0, 'Clark', 'Mari', NULL,'2012-08-01 08:44:42'),
(1,	'Smith', 'Darren', NULL, '2012-07-02 12:08:23' 	),
(2,	'Smith', 'Tracy', NULL,	'2012-07-02 12:08:23'),
(3,	'Rownam', 'Tim', NULL,'2012-07-03 09:32:15'),
(4,	'Joplette', 'Janice', 1,'2012-07-03 10:25:05'),
(5,	'Butters',	'Gerald', 1, '2012-07-09 10:44:09');

CREATE TABLE cd.bookings
(
    facility_id integer NOT null,
    id integer NOT null,
    start_time timestamp NOT null,
    slots integer NOT null,
    CONSTRAINT fk_bookings_facility_id foreign key (facility_id) references facilities(facility_id)
    CONSTRAINT fk_bookings_id foreign key (id) references members(id)
);

INSERT INTO cd.bookings (facility_id, id, start_time, slots)
VALUES (0, 3, 1, '2012-07-03 11:00:00', 2),
(1, 4, 1, '2012-07-03 08:00:00', 2),
(2, 6, 0, '2012-07-04 18:00:00', 2),
(3, 7, 1, '2012-08-04 19:00:00', 3),
(4, 8, 1, '2012-07-05 10:00:00', 1),
(5, 8, 1, '2012-08-05 15:00:00', 2);

SELECT name, revenue FROM
(SELECT facs.name, SUM(books.slots *
  CASE WHEN books.id = 0 THEN facs.guest_cost 
  ELSE facs.member_cost
  END) AS revenue
FROM cd.facilities facs
JOIN cd.bookings books ON books.facility_id = facs.facility_id
GROUP BY facs.name) as rev 
WHERE revenue < 1000
ORDER BY revenue