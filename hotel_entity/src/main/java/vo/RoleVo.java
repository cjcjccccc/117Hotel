package vo;

import entity.Role;
import lombok.Data;

/**
 * @author 陈境聪
 * @date 2021-05-18 22:00
 */
@Data
public class RoleVo extends Role {
    private Integer page;//当前页
    private Integer limit;//展示条数
}
