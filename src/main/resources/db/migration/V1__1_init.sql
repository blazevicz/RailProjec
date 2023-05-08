create table line_entry
(
    id              serial
        primary key
        unique,
    end_kilometer   double precision,
    end_station     varchar(255),
    line_number     integer,
    page            integer,
    railway_region  integer,
    start_kilometer double precision,
    start_station   varchar(255)
);
