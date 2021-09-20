package com.softfabrique.test.service.database1.impl;

import com.softfabrique.test.entity.database1.Db1Table;
import com.softfabrique.test.mapper.database1.Db1TableMapper;
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
 * @since 2021-09-20
 */
@Service
public class Db1TableServiceImpl extends ServiceImpl<Db1TableMapper, Db1Table>{

    public IPage selectSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectSimple(pagination,params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectMapSimple(pagination,params));
        return pagination;
    }
}
