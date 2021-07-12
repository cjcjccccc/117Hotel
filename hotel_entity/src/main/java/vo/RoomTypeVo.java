package vo;

import entity.RoomType;
import lombok.Data;

/**
 * @author 陈境聪
 * @date 2021-05-26 20:59
 */
@Data
public class RoomTypeVo extends RoomType {
    private Integer page;//当前页
    private Integer limit;//展示条数
}
