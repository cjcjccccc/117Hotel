package vo;

import entity.Room;
import lombok.Data;

/**
 * @author 陈境聪
 * @date 2021-05-27 10:42
 */
@Data
public class RoomVo extends Room {
    private Integer page;//当前页
    private Integer limit;//展示条数
}
