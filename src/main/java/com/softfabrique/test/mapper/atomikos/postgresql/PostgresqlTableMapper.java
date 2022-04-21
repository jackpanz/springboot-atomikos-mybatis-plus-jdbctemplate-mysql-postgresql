package com.softfabrique.test.mapper.atomikos.postgresql;

import com.softfabrique.test.entity.postgresql.PostgresqlTable;
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
 * @since 2022-04-21
 */
public interface PostgresqlTableMapper extends BaseMapper<PostgresqlTable> {

    List selectSimple(IPage pagination, @Param("p") Map params);

    List selectMapSimple(IPage pagination, @Param("p") Map params);

}
