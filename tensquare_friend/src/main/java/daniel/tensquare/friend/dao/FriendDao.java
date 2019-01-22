package daniel.tensquare.friend.dao;

import daniel.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface FriendDao extends JpaRepository<Friend,String> {
    public int countByuseridAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "update tb_friend set islike=?3 where userid=?1 and friendid=?2",nativeQuery = true)
    public void updateLike(String userid,String friendid,String islike);

    @Modifying
    @Query(value = "delete from tb_friend where userid=?1 and friendid=?2",nativeQuery = true)
    public void deleteFriend(String userid,String friendid);
}
