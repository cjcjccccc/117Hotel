package entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 订单类
 * @author 陈境聪
 * @date 2021-05-28 20:30
 */
@Data
public class Orders {
    private Long id;
    private String ordersNo;
    private Long accountId;
    private Integer roomTypeId;
    private Integer roomId;
    private String reservationName;
    private String idCard;
    private String phone;
    private Integer status;
    private Date reserveDate;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arriveDate;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;
    private Double reservePrice;
    private String remark;

    //添加房型对象
    private RoomType roomType;
    //添加房间对象
    private Room room;
}
