package daniel.tensquare.friend.service;

import daniel.tensquare.friend.dao.NoFriendDao;
import daniel.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoFriendService {
    @Autowired
    private NoFriendDao noFriendDao;

    public int addNoFriend(String userid,String friendid){
        if(noFriendDao.countByUseridAndFriendid(userid,friendid)>0){
            return 0;
        }else{
            NoFriend noFriend=new NoFriend();
            noFriend.setUserid(userid);
            noFriend.setFriendid(friendid);
            noFriendDao.save(noFriend);
            return 1;
        }
    }
}
