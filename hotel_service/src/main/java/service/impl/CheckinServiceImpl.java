package service.impl;

import dao.CheckinMapper;
import dao.OrdersMapper;
import dao.RoomMapper;
import dao.RoomTypeMapper;
import entity.Checkin;
import entity.Orders;
import entity.Room;
import entity.RoomType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CheckinService;
import vo.CheckinVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-29 11:20
 */
@Service
@Transactional
public class CheckinServiceImpl implements CheckinService {
    @Resource
    private CheckinMapper checkinMapper;
    @Resource
    private OrdersMapper ordersMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private RoomMapper roomMapper;

    @Override
    public List<Checkin> findCheckinList(CheckinVo checkinVo) {
        return checkinMapper.findCheckinList(checkinVo);
    }

    @Override
    public int addCheckin(Checkin checkin) {
        //设置状态
        checkin.setStatus(1);//入住中
        checkin.setCreateDate(new Date());//创建时间
        int count = checkinMapper.addCheckin(checkin);
        if (count >0) {
            //修改预订订单信息(从已确认的状态2改成入住中3)
            Orders orders = new Orders();
            orders.setId(checkin.getOrdersId());
            orders.setStatus(3);//入住中
            //调用修改订单信息的方法
            ordersMapper.update(orders);

            //修改房型信息(在原有的已入住数量的基础上+1)
            RoomType roomType = roomTypeMapper.getRoomTypeById(checkin.getRoomTypeId());
            roomType.setLivedNum(roomType.getLivedNum()+1);
            roomType.setAvilableNum(roomType.getAvilableNum()-1);
            //调用修改房型的方法
            roomTypeMapper.updateRoomType(roomType);

            //修改房间状态（由已预订2改成入住中3）
            Room room = new Room();
            room.setStatus(3);
            room.setId(Math.toIntExact(checkin.getRoomId()));
            System.out.println(room);
            //调用修改的方法
            roomMapper.updateRoom(room);
        }
        return count;
    }
}
