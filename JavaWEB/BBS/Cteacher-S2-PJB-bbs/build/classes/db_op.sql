--统计6号版块下所有的贴子数
select count( * ) from tbl_topic where boardid=6;

--查询6号版块最近的贴子.
select * from (
select topicid as recenttopicid,title as recenttopictitle,tbl_user.userid as recenttopicuserid,uname as recenttopicusername, modifytime as recenttopicmodifytime
 from tbl_topic
  left join tbl_user  
  on tbl_topic.userid=tbl_user.userid 
   where tbl_topic.boardid=6
    order by recenttopicmodifytime desc
    ) where rownum<=1
    
    
--查询某版块下所有的贴子
select topicid,title,content,publishtime,modifytime,tbl_topic.userid,boardid , uname
from tbl_topic 
left join tbl_user
on tbl_user.userid=tbl_topic.userid
where boardid=6;

--分页查询某版块下贴子   每页3条
--  pageSize=3     pageNo=1
--第一页
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

--第二页
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