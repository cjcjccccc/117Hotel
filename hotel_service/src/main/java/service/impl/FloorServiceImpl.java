package service.impl;

import dao.FloorMapper;
import entity.Floor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.FloorService;
import vo.FloorVo;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 陈境聪
 * @date 2021-05-26 20:21
 */
@Service
@Transactional
public class FloorServiceImpl implements FloorService {
    @Resource
    private FloorMapper floorMapper;
    @Override
    public List<Floor> findFloorList(FloorVo floorVo) {
        return floorMapper.findFloorList(floorVo);
    }

    @Override
    public int insert(Floor floor) {
        return floorMapper.insert(floor);
    }

    @Override
    public int updateFloor(Floor floor) {
        return floorMapper.updateFloor(floor);
    }
}
