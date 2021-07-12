package service;

import entity.RoomType;
import org.apache.ibatis.annotations.Param;
import vo.RoomTypeVo;

import java.util.List;

public interface RoomTypeService {
    /*
     * 查询房型列表
     * @author cjc
     */
    List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo);
    /*
     * 添加房型
     * @author cjc
     */
    int insert(RoomType roomType);
    /*
     * 修改房型
     * @author cjc
     */
    int updateRoomType(RoomType roomType);
    /*
     * 删除房型
     * @author cjc
     */
    int deleteRoomTypeById(Integer id);
    /**
     * 根据房型ID查询该房型下的房间数量
     * @param roomTypeId
     * @return
     */
    int getRoomCountByRoomTypeId(Integer roomTypeId);
}
