package daniel.tensquare.recruit.dao;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import daniel.tensquare.recruit.pojo.Enterprise;

import java.util.List;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface EnterpriseDao extends JpaRepository<Enterprise,String>,JpaSpecificationExecutor<Enterprise>{
    /**
     * 查询热门企业
     * @param isHot
     * @return
     */
	public List<Enterprise> findByIshot(String isHot);
}
