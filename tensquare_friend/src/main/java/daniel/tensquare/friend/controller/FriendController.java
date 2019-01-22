package daniel.tensquare.friend.controller;

import daniel.tensquare.friend.dao.FriendDao;
import daniel.tensquare.friend.dao.NoFriendDao;
import daniel.tensquare.friend.service.FriendService;
import daniel.tensquare.friend.service.NoFriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping("/friend")
public class FriendController {
    @Autowired
    private FriendService friendService;
    @Autowired
    private NoFriendService noFriendService;

    @Autowired
    private HttpServletRequest request;
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result addFriend(@PathVariable String type,@PathVariable String friendid){
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        if(user_claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }
        if(user_claims.getId().equals(friendid)){
            return new Result(false, StatusCode.ERROR, "不能添加自己为好友");
        }
        if(type.equals("1")){
            int res = friendService.addFriend(user_claims.getId(), friendid);
            if(res==0){
                return new Result(false,StatusCode.REPERROR,"重复添加好友");
            }else if(res==1){
                return new Result(true,StatusCode.OK,"添加成功");
            }
        }else if(type.equals("2")){
            int res = noFriendService.addNoFriend(user_claims.getId(), friendid);
            if(res==0){
                return new Result(false,StatusCode.REPERROR,"重复添加非好友");
            }else if(res==1){
                return new Result(true,StatusCode.OK,"添加非好友成功");
            }
        }
        return new Result(false,StatusCode.ERROR,"非法参数");
    }

    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid){
        Claims user_claims = (Claims) request.getAttribute("user_claims");
        if(user_claims==null){
            return new Result(false, StatusCode.ACCESSERROR,"权限不足");
        }

        int res = friendService.deleteFriend(user_claims.getId(), friendid);
        if(res==0){
            return new Result(false,StatusCode.REPERROR,"你们已不是好友");
        }else {
            return new Result(true,StatusCode.OK,"删除成功");
        }
    }
}
