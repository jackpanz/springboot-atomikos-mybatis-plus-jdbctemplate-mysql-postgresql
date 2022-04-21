# 整合spring atomikos mybatisPlus实现分布式事务和单数据库事务(mysql postgresql sqlserver)
- spring boot 2.5.4
- mybatis-plus 3.4.3.3
- mysql 5.7
- postgresql 13
- sqlserver 2016
- atomikos
- 整合单数据库事务、多数据库事务。

关键就是把单数据库事务和多数据库事务分开，AtomikosDataSource效能比较低。

![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/1.png?raw=true)

# sqlserver XA配置
* ### 控制面板->管理工具->组件服务
* ### 控制台根节点->组件服务->计算机->我的电脑->Distributed Transaction Coordinator->本地 DTC->右键属性
* ### 安全->启用XA事务
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver1.png?raw=true)
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver2.png?raw=true)
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver3.png?raw=true)
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver4.png?raw=true)
* ### 下载最新版本的的Microsoft JDBC Driver for SQL Server，并且解压,把sqljdbc_xa.dll 拷贝到MSSSQL\BInn
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver5.png?raw=true)
* ###  在运行xa_install.sql脚本
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver6.png?raw=true)
![image](https://github.com/jackpanz/springboot-atomikos-mybatis-plus-jdbctemplate-mysql-postgresql/blob/master/doc/sqlserver7.png?raw=true)


# Postgresql配置
* ### 找到data目录下的postgresql.conf加入max_prepared_transactions = 10
