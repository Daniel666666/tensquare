package daniel.tensquare.base.service;

import daniel.tensquare.base.dao.LabelDao;
import daniel.tensquare.base.pojo.Label;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;
    /**
     * 查询全部标签
     * @return
     */
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    /**
     * 根据ID查询标签
     * @return
     */
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    /**
     * 增加标签
     * @param label
     */
    public void add(Label label){
        label.setId( idWorker.nextId()+"" );//设置ID
        labelDao.save(label);
    }
    /**
     * 修改标签
     * @param label
     */
    public void update(Label label){
        labelDao.save(label);
    }
    /**
     * 删除标签
     * @param id
     */
    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    /**
     * 搜索标签
     * @param searchMap
     * @return
     */
    public List<Label> findSearch(Map searchMap){
        Specification<Label> specification = createSpecification(searchMap);
        List<Label> labelList = labelDao.findAll(specification);
        return labelList;
    }

    /**
     * 分页搜索
     * @param searchMap
     * @param page
     * @param size
     * @return
     */
    public Page<Label> findSearch(Map searchMap,int page,int size){
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        Specification<Label> specification = createSpecification(searchMap);
        Page<Label> labelPage = labelDao.findAll(specification, pageRequest);
        return labelPage;
    }

    private Specification<Label> createSpecification(Map searchMap){
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> predicateList=new ArrayList<>();
                if(searchMap.get("labelname")!=null&&searchMap.get("labelname")!=""){
                    predicateList.add(cb.like(root.get("labelname").as(String.class),"%"+searchMap.get("labelname")+"%"));
                }
                if(searchMap.get("state")!=null&&searchMap.get("state")!=""){
                    predicateList.add(cb.equal(root.get("state").as(String.class),"%"+searchMap.get("state")+"%"));
                }
                if(searchMap.get("recommend")!=null&&searchMap.get("recommend")!=""){
                    predicateList.add(cb.equal(root.get("recommend").as(String.class),"%"+searchMap.get("recommend")+"%"));
                }
                return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }
}

