package com.softfabrique.test.entity.postgresql;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2022-04-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_postgresql_table")
@KeySequence(value = "seq_postgresql_table", dbType = DbType.POSTGRE_SQL)
public class PostgresqlTable implements Serializable {


    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private String name;

    private Integer age;

    private Date create_date;

}
