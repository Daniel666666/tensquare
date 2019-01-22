package daniel.tensquare.search.Controller;

import daniel.tensquare.search.Pojo.Article;
import daniel.tensquare.search.Service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {
    @Autowired
    private ArticleSearchService articleSearchService;

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        articleSearchService.add(article);
        return new Result(true, StatusCode.OK,"增加成功");
    }

    @RequestMapping(value = "/search/{key}/{page}/{size}",method = RequestMethod.GET)
    public Result search(@PathVariable String key,@PathVariable int page,@PathVariable int size){
        Page<Article> pageData = articleSearchService.search(key, page, size);
        return new Result(true, StatusCode.OK, "搜索成功", new PageResult<>(pageData.getTotalElements(), pageData.getContent()));
    }
}
