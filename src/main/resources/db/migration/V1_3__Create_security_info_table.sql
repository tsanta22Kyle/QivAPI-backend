CREATE TABLE IF NOT EXISTS security_info(
                                            id varchar primary key ,
                                            ssl boolean,
                                            hsts boolean,
                                            no_sniff boolean
)