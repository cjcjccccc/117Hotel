package service;

import entity.Floor;
import vo.FloorVo;

import java.util.List;

public interface FloorService {
    /*
     *查询楼层列表
     * @author cjc
     */
    List<Floor> findFloorList(FloorVo floorVo);
    /*
     * 添加楼层
     * @author cjc
     */
    int insert(Floor floor);
    /*
     * 修改楼层
     * @author cjc
     */
    int updateFloor(Floor floor);
}
