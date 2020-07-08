## walt社区

## 资料
[spring 文档](https://spring.io/guides)<br>
[bootstrap 文档](https://v3.bootcss.com/components/#navbar)<br>
[github OAuth 文档](https://developer.github.com/apps/building-oauth-apps/)<br>

## 工具
[UML](https://www.visual-paradigm.com/cn/download/community.jsp)<br>
[H2数据库](http://www.h2database.com/html/main.html)<br>
[flyway](https://flywaydb.org/getstarted/firststeps/maven)<br>

## 脚本
```sql
create table USER
(
	ID INT auto_increment,
	ACCOUNT_ID VARCHAR(100),
	NAME VARCHAR(50),
	TOKEN CHAR(36),
	GMT_CREATE BIGINT,
	GMT_MODIFIED BIGINT,
	constraint USER_PK
		primary key (ID)
);
```
```shell script
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
