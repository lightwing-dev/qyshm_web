package net.lightwing.qyshm_web.service;

import com.baomidou.mybatisplus.plugins.Page;
import net.lightwing.qyshm_web.commons.util.PageInfo;
import net.lightwing.qyshm_web.dao.QBottommenuDao;
import net.lightwing.qyshm_web.pojo.QBottommenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

@SuppressWarnings("ALL")
@Service
@Transactional(rollbackFor = Exception.class)
public class QBottommenuService {

    @Autowired
    private QBottommenuDao qBottommenuDao;

    public PageInfo selectPageInfo(PageInfo pageInfo) {
        pageInfo.setSort("bmid");
        Page<Map<String, Object>> page = new Page<>(pageInfo.getNowpage(), pageInfo.getPagesize());
        page.setOrderByField(pageInfo.getSort());//排序字段
        pageInfo.setOrder("asc");//排序方式
        page.setAsc(pageInfo.getOrder().equalsIgnoreCase("desc"));
        pageInfo.setRows(qBottommenuDao.selectPage(page, pageInfo.getCondition()));//获取数据
        pageInfo.setTotal(page.getTotal());//获取总条数
        return pageInfo;
    }

    public int insert(QBottommenu qBottommenu) {
        return qBottommenuDao.insertSelective(qBottommenu);
    }

    public int update(QBottommenu qBottommenu) {
        Example example = new Example(QBottommenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bmid", qBottommenu.getBmid());
        return qBottommenuDao.updateByExample(qBottommenu,example);
    }

    public int delete(Integer bmid) {
        Example example = new Example(QBottommenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bmid", bmid);
        return qBottommenuDao.deleteByExample(example);
    }

    public QBottommenu selectById(Integer bmid) {
        Example example = new Example(QBottommenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bmid", bmid);
        return qBottommenuDao.selectOneByExample(example);
    }

    public List<QBottommenu> selectByName(String name) {
        Example example = new Example(QBottommenu.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bmtitle", name);
        return qBottommenuDao.selectByExample(example);
    }
}
