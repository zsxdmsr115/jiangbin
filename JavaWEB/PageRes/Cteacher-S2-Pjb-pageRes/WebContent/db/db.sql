--------------------------------------------------------------------
--                �����⣬��Լ�������̣��û���Ȩ�޵Ƚű�
--------------------------------------------------------------------

--normprice:ԭ��  realprice:�ּ�   description:��� detail��ϸ��
create table resfood(
	fid int  primary key auto_increment,
	fname varchar(50) ,  
	normprice numeric(8,2),
	realprice numeric(8,2),
	detail varchar(2000),
	fphoto varchar(1000)
);
