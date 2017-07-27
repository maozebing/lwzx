package gsunis.info.dao.demo;


import gsunis.info.common.dbentity.demo.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/4/12 16:29
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
public interface TestDao extends JpaRepository<TestEntity,Long> {
    List<TestEntity> findByName(String name);
}
