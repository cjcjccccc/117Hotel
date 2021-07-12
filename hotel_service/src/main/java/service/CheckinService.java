package service;

import entity.Checkin;
import vo.CheckinVo;

import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-29 11:19
 */
public interface CheckinService {
    /**
     * 查询入住信息列表
     * @author cjc
     */
    List<Checkin> findCheckinList(CheckinVo checkinVo);
    /**
     * 添加入住信息
     * @author cjc
     */
    int addCheckin(Checkin checkin);
}
