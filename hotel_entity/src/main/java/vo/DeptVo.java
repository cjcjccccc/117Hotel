package vo;

import entity.Dept;
import lombok.Data;

/**
 * @author 陈境聪
 * @date 2021-05-18 17:03
 */
@Data
public class DeptVo extends Dept {
    private Integer page;//当前页
    private Integer limit;//展示条数
}
