package service.impl;

import dao.RoomMapper;
import entity.Room;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RoomService;
import vo.RoomVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-27 10:57
 */
@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomMapper roomMapper;
    @Override
    public List<Room> findRoomList(RoomVo roomVo) {
        return roomMapper.findRoomList(roomVo);
    }

    @Override
    public int insert(Room room) {
        return roomMapper.insert(room);
    }

    @Override
    public int updateRoom(Room room) {
        return roomMapper.updateRoom(room);
    }

    @Override
    public int deleteRoomById(Integer id) {
        return roomMapper.deleteRoomById(id);
    }

    @Override
    public Room findRoomById(Integer id) {
        return roomMapper.findRoomById(id);
    }
}
