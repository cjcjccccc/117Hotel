package service.impl;

import dao.*;
import entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.CheckoutService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author 陈境聪
 * @date 2021-05-29 18:21
 */
@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
    @Resource
    private CheckoutMapper checkoutMapper;
    @Resource
    private CheckinMapper checkinMapper;
    @Resource
    private RoomTypeMapper roomTypeMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private OrdersMapper ordersMapper;

    @Override
    public int addCheckout(Checkout checkout) {
        checkout.setCheckOutNumber(UUID.randomUUID().toString().replace("-",""));
        checkout.setCreateDate(new Date());
        int count = checkoutMapper.addCheckout(checkout);
        if (count >0) {
            //修改入住登记表(t_checkin)中的状态((从已入住的状态1改成入住中2已离店)
            Checkin checkin = checkinMapper.findById(checkout.getCheckInId());
            checkin.setStatus(2);
            checkinMapper.update(checkin);

            //修改房型信息(可用房间数+1,已入住房间数-1)
            RoomType roomType = roomTypeMapper.getRoomTypeById(checkin.getRoomTypeId());
            roomType.setLivedNum(roomType.getLivedNum()-1);
            roomType.setAvilableNum(roomType.getAvilableNum()+1);
            roomTypeMapper.updateRoomType(roomType);

            //修改房间状态（由入住中3改为1）
            Room room = new Room();
            room.setStatus(1);
            room.setId(Math.toIntExact(checkin.getRoomId()));
            roomMapper.updateRoom(room);

            //修改预订表状态(t_orders)的状态【由3改成4】
            Orders orders = new Orders();
            orders.setId(checkin.getOrdersId());
            orders.setStatus(4);
            ordersMapper.update(orders);
        }
        return count;
    }
}
