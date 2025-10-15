create table if not exists api_health(
                                         id varchar primary key ,
                                         is_active boolean,
                                         is_api boolean,
                                        security_info_id varchar,
                                         api_id varchar ,
                                         CONSTRAINT api_fk FOREIGN KEY (api_id) REFERENCES "api"(id),
                                         CONSTRAINT security_info_fk FOREIGN KEY (security_info_id) REFERENCES security_info(id)
)