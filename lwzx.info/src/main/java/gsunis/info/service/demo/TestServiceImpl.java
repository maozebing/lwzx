package gsunis.info.service.demo;


import gsunis.info.common.dbentity.demo.TestEntity;
import gsunis.info.dao.demo.TestDao;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
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
@Service
@Transactional
@CacheConfig(cacheNames = "test")
public class TestServiceImpl implements TestService{

    @Resource
    private TestDao testDao;

    @Override
    public TestEntity getTest(long id) {
        return testDao.findOne(id);
    }

    @Cacheable
    @Override
    public List<TestEntity> listTest() {
        return testDao.findAll();
    }

    @CacheEvict(allEntries = true)
    @Override
    public void saveTest(TestEntity entity) {
        testDao.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public void updateTest(TestEntity entity) {
        testDao.save(entity);
    }

    @CacheEvict(allEntries = true)
    @Override
    public void removeTest(long id) {
        testDao.delete(id);
    }

    @Override
    public List<TestEntity> findByName(String name) {
        return testDao.findByName(name);
    }
}
