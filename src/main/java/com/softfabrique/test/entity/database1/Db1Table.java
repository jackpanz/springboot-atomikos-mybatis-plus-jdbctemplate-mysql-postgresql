package com.softfabrique.test.entity.database1;
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
 * @since 2021-09-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("public.t_db1_table")
@KeySequence(value = "seq_db1_table", dbType = DbType.POSTGRE_SQL)
public class Db1Table implements Serializable {


    @TableId(value = "id", type = IdType.INPUT)
    private Integer id;

    private String title;

    private Date create_date;

    private Integer status;

}
