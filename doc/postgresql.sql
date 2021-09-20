/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-postgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 130004
 Source Host           : localhost:5432
 Source Catalog        : db1
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 130004
 File Encoding         : 65001

 Date: 20/09/2021 16:03:04
*/


-- ----------------------------
-- Sequence structure for seq_db1_table
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."seq_db1_table";
CREATE SEQUENCE "public"."seq_db1_table" 
INCREMENT 1
MINVALUE  1
MAXVALUE 9223372036854775807
START 1
CACHE 1;

-- ----------------------------
-- Table structure for t_db1_demo
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_db1_demo";
CREATE TABLE "public"."t_db1_demo" (
  "name" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Table structure for t_db1_table
-- ----------------------------
DROP TABLE IF EXISTS "public"."t_db1_table";
CREATE TABLE "public"."t_db1_table" (
  "id" int4 NOT NULL,
  "title" varchar(255) COLLATE "pg_catalog"."default",
  "create_date" timestamp(6),
  "status" int2
)
;

-- ----------------------------
-- Primary Key structure for table t_db1_table
-- ----------------------------
ALTER TABLE "public"."t_db1_table" ADD CONSTRAINT "t_db1_table_pkey" PRIMARY KEY ("id");
