package entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 登记入住
 * @author 陈境聪
 * @date 2021-05-29 11:10
 */
@Data
public class Checkin {
    private Long id;
    private Double payPrice;
    private Integer roomTypeId;
    private Long roomId;
    private String customerName;
    private String idCard;
    private String phone;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date arriveDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date leaveDate;
    private Long ordersId;
    private Integer status;
    private Date createDate;
    private Integer createdBy;
    private Date modifyDate;
    private Integer modifyBy;
    private String remark;

    //房型对象
    private RoomType roomType;
    //房间对象
    private Room room;
}
