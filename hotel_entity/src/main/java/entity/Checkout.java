package entity;

import lombok.Data;

import java.util.Date;

/**
 * 退房
 * @author 陈境聪
 * @date 2021-05-29 18:10
 */
@Data
public class Checkout {
    private Long id;
    private String checkOutNumber;
    private Long checkInId;
    private Double consumePrice;
    private Date createDate;
    private Integer createdBy;
    private String remark;
}
