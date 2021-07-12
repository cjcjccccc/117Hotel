package dao;

import entity.Checkin;
import vo.CheckinVo;

import java.util.List;

public interface CheckinMapper {
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
    /**
     * 根据id查找入住信息
     * @author cjc
     */
    Checkin findById(Long checkInId);
    /**
     * 修改入住信息
     * @author cjc
     */
    int update(Checkin checkin);
}
