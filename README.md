# code-along-jdbc-crud



CREATE SEQUENCE seq_blog
MINVALUE 1
START WITH 1
INCREMENT BY 1
CACHE 10;

CREATE TABLE blog(
blogId NUMBER(20) NOT NULL,
blogTitle VARCHAR2(70) not null,
blogDescription CLOB,
postedOn DATE not null,
PRIMARY KEY(blogId)
)
