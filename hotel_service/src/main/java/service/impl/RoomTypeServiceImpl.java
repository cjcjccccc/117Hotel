package service.impl;

import dao.RoomTypeMapper;
import entity.RoomType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.RoomTypeService;
import vo.RoomTypeVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-26 21:06
 */
@Service
@Transactional
public class RoomTypeServiceImpl implements RoomTypeService {

    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Override
    public List<RoomType> findRoomTypeList(RoomTypeVo roomTypeVo) {
        return roomTypeMapper.findRoomTypeList(roomTypeVo);
    }

    @Override
    public int insert(RoomType roomType) {
        //ReservedNum已预订数量,AvilableNum可入住房间数量,livedNum已入住房间数量 没有被赋值
        roomType.setReservedNum(0);
        roomType.setAvilableNum(roomType.getRoomNum());
        roomType.setLivedNum(0);
        return roomTypeMapper.insert(roomType);
    }

    @Override
    public int updateRoomType(RoomType roomType) {
        //ReservedNum已预订数量,AvilableNum可入住房间数量,livedNum已入住房间数量 没有被赋值
        roomType.setReservedNum(0);
        roomType.setAvilableNum(roomType.getRoomNum());
        roomType.setLivedNum(0);
        return roomTypeMapper.updateRoomType(roomType);
    }

    @Override
    public int deleteRoomTypeById(Integer id) {
        return roomTypeMapper.deleteRoomTypeById(id);
    }

    @Override
    public int getRoomCountByRoomTypeId(Integer roomTypeId) {
        return roomTypeMapper.getRoomCountByRoomTypeId(roomTypeId);
    }
}
