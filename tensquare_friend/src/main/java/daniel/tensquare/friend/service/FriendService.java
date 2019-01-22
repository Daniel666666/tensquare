package daniel.tensquare.friend.service;

import daniel.tensquare.friend.client.UserClient;
import daniel.tensquare.friend.dao.FriendDao;
import daniel.tensquare.friend.pojo.Friend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FriendService {
    @Autowired
    private FriendDao friendDao;

    @Autowired
    private UserClient userClient;
    public int addFriend(String userid, String friendid) {
        if(friendDao.countByuseridAndFriendid(userid,friendid)>0){
            return 0;
        }else{
            Friend friend=new Friend();
            friend.setUserid(userid);
            friend.setFriendid(friendid);
            friend.setIslike("0");
            friendDao.save(friend);
            if(friendDao.countByuseridAndFriendid(friendid,userid)>0){
                friendDao.updateLike(userid,friendid,"1");
                friendDao.updateLike(friendid,userid,"1");
            }
            userClient.incFollowcount(userid,1);
            userClient.incFanscount(friendid,1);
            return 1;
        }
    }

    public int deleteFriend(String userid,String friendid){
        if(friendDao.countByuseridAndFriendid(userid,friendid)==0){
            return 0;
        }else{
            friendDao.deleteFriend(userid,friendid);
            friendDao.updateLike(friendid,userid,"0");
            userClient.incFollowcount(userid,-1);
            userClient.incFanscount(friendid,-1);
            return 1;
        }
    }
}
