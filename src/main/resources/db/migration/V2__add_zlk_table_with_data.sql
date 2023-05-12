create table zlk
(
    id              serial
        primary key
        unique,
    zlkRegionNumber int not null unique,
    actualWOS       text,
    actualWOSlink   text
);


INSERT INTO zlk (zlkRegionNumber)
VALUES (1);
INSERT INTO zlk (zlkRegionNumber)
VALUES (2);
INSERT INTO zlk (zlkRegionNumber)
VALUES (3);
INSERT INTO zlk (zlkRegionNumber)
VALUES (4);
INSERT INTO zlk (zlkRegionNumber)
VALUES (5);
INSERT INTO zlk (zlkRegionNumber)
VALUES (6);
INSERT INTO zlk (zlkRegionNumber)
VALUES (7);
INSERT INTO zlk (zlkRegionNumber)
VALUES (8);
