
create table if not exists "api"(
    id varchar primary key ,
    name varchar ,
    url varchar ,
    description varchar ,
    created TIMESTAMP ,
    updated TIMESTAMP ,
    user_id varchar ,
    CONSTRAINT user_fk FOREIGN KEY (user_id) REFERENCES "user"(id)
);


