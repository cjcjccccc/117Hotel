package dao;

import entity.Checkout;

import java.util.List;

public interface CheckoutMapper {
    /**
     * 添加退房记录
     * @author cjc
     */
    int addCheckout(Checkout checkout);
}
