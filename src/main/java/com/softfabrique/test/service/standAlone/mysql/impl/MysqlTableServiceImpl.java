package com.softfabrique.test.service.standAlone.mysql.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.jackpanz.spring.util.MException;
import com.softfabrique.test.entity.mysql.MysqlTable;
import com.softfabrique.test.mapper.standAlone.mysql.MysqlTableMapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author
 * @since 2022-04-20
 */
@Service
public class MysqlTableServiceImpl extends ServiceImpl<MysqlTableMapper, MysqlTable> {

    public IPage selectSimple(IPage pagination, Map params) {
        pagination.setRecords(baseMapper.selectSimple(pagination, params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params) {
        pagination.setRecords(baseMapper.selectMapSimple(pagination, params));
        return pagination;
    }

    @Transactional("mysqlTransaction")
    public void saveStandAlone() {
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("StandAlone");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        save(mysqlTable);
    }

    @Transactional("mysqlTransaction")
    public void saveStandAloneEx() {
        MysqlTable mysqlTable = new MysqlTable();
        mysqlTable.setName("StandAloneEx");
        mysqlTable.setAge(1);
        mysqlTable.setCreate_date(new Date());
        save(mysqlTable);
        throw new MException("异常");
    }

}
