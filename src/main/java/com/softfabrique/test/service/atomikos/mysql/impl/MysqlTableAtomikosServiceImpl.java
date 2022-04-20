package com.softfabrique.test.service.atomikos.mysql.impl;

import com.softfabrique.test.entity.mysql.MysqlTable;
import com.softfabrique.test.mapper.atomikos.mysql.MysqlTableAtomikosMapper;
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
 * @since 2022-04-20
 */
@Service
public class MysqlTableAtomikosServiceImpl extends ServiceImpl<MysqlTableAtomikosMapper, MysqlTable>{

    public IPage selectSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectSimple(pagination,params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params){
        pagination.setRecords(baseMapper.selectMapSimple(pagination,params));
        return pagination;
    }


}
