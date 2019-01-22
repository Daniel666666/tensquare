package daniel.tensquare.spit.Dao;

import daniel.tensquare.spit.Pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SpitDao extends MongoRepository<Spit,String> {

    public Page<Spit> findByParentid(String id, Pageable pageable);
}
