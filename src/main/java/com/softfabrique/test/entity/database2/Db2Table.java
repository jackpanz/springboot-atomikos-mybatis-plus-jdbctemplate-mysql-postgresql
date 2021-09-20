package com.softfabrique.test.entity.database2;
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
@TableName("t_db2_table")
public class Db2Table implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String title;

    private Date create_date;

    private Integer status;

}
