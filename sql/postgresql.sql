CREATE SEQUENCE "public"."seq_postgresql_table";

DROP TABLE IF EXISTS "public"."t_postgresql_table";
CREATE TABLE "public"."t_postgresql_table" (
  "id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "age" int4,
  "create_date" date
)
