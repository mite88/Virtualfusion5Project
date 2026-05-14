CREATE TABLE post
(
    id              BIGINT AUTO_INCREMENT NOT NULL,
    title           VARCHAR(255)          NULL,
    content         TEXT                  NULL,
    CONSTRAINT pk_post PRIMARY KEY (id)
);