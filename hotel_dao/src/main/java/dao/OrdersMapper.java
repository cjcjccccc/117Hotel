package dao;

import entity.Orders;
import vo.OrdersVo;

import java.util.List;

public interface OrdersMapper {
    /**
     * 添加订单
     * @author cjc
     */
    int addOrders(Orders orders);
    /**
     * 查询订单列表
     * @author cjc
     */
    List<Orders> findOrdersList(OrdersVo ordersVo);
    /**
     * 确认订单
     * @author cjc
     */
    int update(Orders orders);
}
