package service.impl;

import dao.OrdersMapper;
import dao.RoomMapper;
import dao.RoomTypeMapper;
import entity.Orders;
import entity.Room;
import entity.RoomType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.OrdersService;
import vo.OrdersVo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author 陈境聪
 * @date 2021-05-28 20:35
 */
@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {
    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private RoomMapper roomMapper;

    @Resource
    private RoomTypeMapper roomTypeMapper;

    @Override
    public int addOrders(Orders orders) {
        //为订单信息赋初值
        orders.setOrdersNo(UUID.randomUUID().toString().replace("-",""));//订单号
        orders.setReserveDate(new Date());//订单创建时间
        orders.setStatus(1);//订单状态为待确认
        //1.添加订单信息
        int i = ordersMapper.addOrders(orders);
        if (i>0) {
            //2.修改房间信息(状态改为:已预订2)
            Room room = roomMapper.getRoomById(orders.getRoomId());
            room.setStatus(2);
            roomMapper.updateRoom(room);
            //3.修改房型信息(可用房间数-1,已预订房间数+1)
            RoomType roomType = roomTypeMapper.getRoomTypeById(orders.getRoomTypeId());
            roomType.setAvilableNum(roomType.getAvilableNum()-1);//可用房间数-1
            roomType.setReservedNum(roomType.getReservedNum()+1);//已预订房间数+1
            roomTypeMapper.updateRoomType(roomType);
        }
        return i;
    }

    @Override
    public List<Orders> findOrdersList(OrdersVo ordersVo) {
        return ordersMapper.findOrdersList(ordersVo);
    }

    @Override
    public int update(Orders orders) {
        return ordersMapper.update(orders);
    }
}
