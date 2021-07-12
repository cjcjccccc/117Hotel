package vo;

import entity.Dept;
import entity.SysUser;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 陈境聪
 * @date 2021-05-18 17:03
 */
@Data
public class UserVo extends SysUser {
    private Integer page;//当前页
    private Integer limit;//展示条数

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
}
