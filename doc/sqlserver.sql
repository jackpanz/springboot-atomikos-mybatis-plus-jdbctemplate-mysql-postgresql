CREATE TABLE [dbo].[t_mmsql_table] (
  [id] int  IDENTITY(1,1) NOT NULL,
  [name] nvarchar(50) COLLATE Chinese_PRC_CI_AS  NULL,
  [age] int  NULL,
  [create_date] datetime  NULL
)