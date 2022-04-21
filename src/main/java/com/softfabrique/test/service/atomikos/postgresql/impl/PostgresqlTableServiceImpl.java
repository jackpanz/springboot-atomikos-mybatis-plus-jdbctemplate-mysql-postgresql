package com.softfabrique.test.service.atomikos.postgresql.impl;

import com.softfabrique.test.entity.postgresql.PostgresqlTable;
import com.softfabrique.test.mapper.atomikos.postgresql.PostgresqlTableMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2022-04-21
 */
@Service
public class PostgresqlTableServiceImpl extends ServiceImpl<PostgresqlTableMapper, PostgresqlTable>{

    public IPage selectSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectSimple(pagination,params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectMapSimple(pagination,params));
        return pagination;
    }
}
