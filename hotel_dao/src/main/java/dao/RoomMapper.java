package dao;

import entity.Room;
import org.apache.ibatis.annotations.Param;
import vo.RoomVo;

import java.util.List;

public interface RoomMapper {
    /**
     *  查询房间列表
     * @author cjc
     */
    List<Room> findRoomList(RoomVo roomVo);
    /**
     * 添加房间
     * @author cjc
     */
    int insert(Room room);
    /**
     * 修改房间
     * @author cjc
     */
    int updateRoom(Room room);
    /**
     * 删除房间
     * @author cjc
     */
    int deleteRoomById(@Param("id") Integer id);
    /**
     * 根据id查找房间信息
     * @author cjc
     */
    Room findRoomById(Integer id);
    /**
     * 根据id查找房间信息
     * @author cjc
     */
    Room getRoomById(Integer roomId);
}
