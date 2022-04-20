package com.softfabrique.test.service.atomikos.mmsql.impl;

import com.softfabrique.test.entity.mmsql.MmsqlTable;
import com.softfabrique.test.mapper.atomikos.mmsql.MmsqlTableMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

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
public class MmsqlTableAtomikosServiceImpl extends ServiceImpl<MmsqlTableMapper, MmsqlTable> {

    public IPage selectSimple(IPage pagination, Map params) {
        pagination.setRecords(baseMapper.selectSimple(pagination, params));
        return pagination;
    }

    public IPage selectMapSimple(IPage pagination, Map params) {
        pagination.setRecords(baseMapper.selectMapSimple(pagination, params));
        return pagination;
    }

}
