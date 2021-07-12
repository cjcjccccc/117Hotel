package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 部门类
 * @author 陈境聪
 * @date 2021-05-18 17:00
 */
@Data
public class Dept {
    private Integer id;
    private String deptName;
    private String address;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createTime;
    private String remark;
}
