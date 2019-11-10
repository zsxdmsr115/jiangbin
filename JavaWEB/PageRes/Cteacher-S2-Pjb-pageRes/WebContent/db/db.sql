--------------------------------------------------------------------
--                创建库，表，约束，过程，用户，权限等脚本
--------------------------------------------------------------------

--normprice:原价  realprice:现价   description:简介 detail详细的
create table resfood(
	fid int  primary key auto_increment,
	fname varchar(50) ,  
	normprice numeric(8,2),
	realprice numeric(8,2),
	detail varchar(2000),
	fphoto varchar(1000)
);
