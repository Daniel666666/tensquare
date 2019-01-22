package daniel.tensquare.spit.Controller;

import daniel.tensquare.spit.Pojo.Spit;
import daniel.tensquare.spit.Service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {
    @Autowired
    private SpitService spitService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.GET)
    public Result findById(@PathVariable String spitId){
        return new Result(true, StatusCode.OK, "查询成功", spitService.findById(spitId));
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String spitId){
        spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    @RequestMapping(value = "/{spitId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String spitId){
        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result updateThumbUp(@PathVariable String spitId){
        System.out.println("控制重复点赞");
        spitService.thumbup(spitId);
        return new Result(true, StatusCode.OK, "点赞成功");
    }

    @RequestMapping(value = "/comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentId(@PathVariable String parentId,@PathVariable int page,@PathVariable int size){
        Page<Spit> pageDate = spitService.findByParentId(parentId, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<>(pageDate.getTotalElements(), pageDate.getContent()));
    }
}
