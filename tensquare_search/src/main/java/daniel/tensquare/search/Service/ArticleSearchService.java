package daniel.tensquare.search.Service;

import daniel.tensquare.search.Dao.ArticleSearchDao;
import daniel.tensquare.search.Pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleSearchDao articleSearchDao;

    @Autowired
    private IdWorker idWorker;
    public void add(Article article){
        article.setId(idWorker.nextId()+"");
        articleSearchDao.save(article);
    }

    public Page<Article> search(String key,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return articleSearchDao.findByTitleOrContentLike(key,key,pageRequest);
    }
}
