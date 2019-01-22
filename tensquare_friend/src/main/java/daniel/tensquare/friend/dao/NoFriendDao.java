package daniel.tensquare.friend.dao;

import daniel.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoFriendDao extends JpaRepository<NoFriend,String> {

    public int countByUseridAndFriendid(String userid,String friendid);
}
