-- 创建表: user，会生成一个名为User的模型类
-- create table if not exists user (
--                                     id long auto_increment primary key,
--                                     name varchar,
--                                     age int
-- )
--     package 'org.example.generated.model' -- User类所在的包名
--     generate code './src/main/java' -- User类的源文件所在的根目录

-- 删除表
drop table if exists `order`;
drop table if exists customer;

set @packageName 'org.example.generated.model';
set @srcPath './src/main/java';

create table if not exists customer (
    id long primary key,
    name char(10),
    notes varchar,
    phone int
) package @packageName generate code @srcPath;
create table if not exists `order` (
    customer_id long,
    order_id int primary key,
    order_date date,
    total double,
    FOREIGN KEY (customer_id) REFERENCES customer(id)
) package @packageName generate code @srcPath;
