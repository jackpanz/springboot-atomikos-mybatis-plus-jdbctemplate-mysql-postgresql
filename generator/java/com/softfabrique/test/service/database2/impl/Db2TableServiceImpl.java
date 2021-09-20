package com.softfabrique.test.service.database2.impl;

import com.softfabrique.test.entity.database2.Db2Table;
import com.softfabrique.test.mapper.database2.Db2TableMapper;
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
public class Db2TableServiceImpl extends ServiceImpl<Db2TableMapper, Db2Table>{

    public IPage selectSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectSimple(pagination,params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectMapSimple(pagination,params));
        return pagination;
    }
}
