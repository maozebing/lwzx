package gsunis.monitor.service.demo;


import gsunis.monitor.common.dbentity.demo.TestEntity;

import java.util.List;

/**
 * 描述 ：
 *
 * @author : maozebing
 * @version : v1.00
 * @CreationDate : 2017/4/12 16:27
 * @Description :
 * @update : 修改人，修改时间，修改内容
 * @see :[相关类/方法]
 */
public interface TestService {
    TestEntity getTest(long id);
    List<TestEntity> listTest();
    void saveTest(TestEntity entity);
    void updateTest(TestEntity entity);
    void removeTest(long id);
    List<TestEntity> findByName(String name);
}
