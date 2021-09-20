package com.softfabrique.test.mapper.database1;

import com.softfabrique.test.entity.database1.Db1Table;
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
 * @since 2021-09-20
 */
public interface Db1TableMapper extends BaseMapper<Db1Table> {

    List selectSimple(IPage pagination, @Param("p") Map params);

    List selectMapSimple(IPage pagination, @Param("p") Map params);

}
