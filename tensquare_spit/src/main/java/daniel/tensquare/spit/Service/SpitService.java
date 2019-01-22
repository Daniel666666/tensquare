package daniel.tensquare.spit.Service;

import com.sun.xml.internal.ws.api.ha.StickyFeature;
import daniel.tensquare.spit.Dao.SpitDao;
import daniel.tensquare.spit.Pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.Date;
import java.util.List;

@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    public void add(Spit spit){
        spit.set_id(idWorker.nextId()+"");
        spit.setComment(0);
        spit.setShare(0);
        spit.setPublishtime(new Date());
        spit.setState("1");
        spit.setThumbup(0);
        spit.setVisits(0);
        if(spit.getParentid()!=null&&spit.getParentid()!=""){

            Query query=new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));
            Update update=new Update();
            update.inc("comment",1);
            mongoTemplate.updateFirst(query, update, "spit");
        }
        spitDao.save(spit);
    }

    public Spit findById(String id){
        return spitDao.findById(id).get();
    }

    public void update(Spit spit){
        spitDao.save(spit);
    }

    public void deleteById(String id){
        spitDao.deleteById(id);
    }

    @Autowired
    private MongoTemplate mongoTemplate;
    public void thumbup(String id){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        Update update=new Update();
        update.inc("thumbup",1);
        mongoTemplate.updateFirst(query,update,"spit");
    }

    public Page<Spit> findByParentId(String id,int page,int size){
        Pageable pageable = PageRequest.of(page - 1, size);
        return spitDao.findByParentid(id, pageable);
    }
}
