package com.softfabrique.test.mapper.atomikos.mmsql;

import com.softfabrique.test.entity.mmsql.MmsqlTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.metadata.IPage;
import java.util.Map;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2022-04-20
 */
public interface MmsqlTableMapper extends BaseMapper<MmsqlTable> {

    List selectSimple(IPage pagination, @Param("p") Map params);

    List selectMapSimple(IPage pagination, @Param("p") Map params);

}
