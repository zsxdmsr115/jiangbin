--ͳ��6�Ű�������е�������
select count( * ) from tbl_topic where boardid=6;

--��ѯ6�Ű�����������.
select * from (
select topicid as recenttopicid,title as recenttopictitle,tbl_user.userid as recenttopicuserid,uname as recenttopicusername, modifytime as recenttopicmodifytime
 from tbl_topic
  left join tbl_user  
  on tbl_topic.userid=tbl_user.userid 
   where tbl_topic.boardid=6
    order by recenttopicmodifytime desc
    ) where rownum<=1
    
    
--��ѯĳ��������е�����
select topicid,title,content,publishtime,modifytime,tbl_topic.userid,boardid , uname
from tbl_topic 
left join tbl_user
on tbl_user.userid=tbl_topic.userid
where boardid=6;

--��ҳ��ѯĳ���������   ÿҳ3��
--  pageSize=3     pageNo=1
--��һҳ
SELECT * FROM 
(
SELECT A.*, ROWNUM RN 
FROM (
select topicid,title,content,publishtime,modifytime,tbl_topic.userid,boardid , uname
from tbl_topic 
left join tbl_user
on tbl_user.userid=tbl_topic.userid
where boardid=?
) A 
WHERE ROWNUM <= ?
)
WHERE RN >= ?

--�ڶ�ҳ
SELECT * FROM 
(
SELECT A.*, ROWNUM RN 
FROM (
select topicid,title,content,publishtime,modifytime,tbl_topic.userid,boardid , uname
from tbl_topic 
left join tbl_user
on tbl_user.userid=tbl_topic.userid
where boardid=6
) A 
WHERE ROWNUM <= 6
)
WHERE RN >=4