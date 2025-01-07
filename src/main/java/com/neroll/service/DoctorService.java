package com.neroll.service;

import com.neroll.mapper.DoctorLevelMapper;
import com.neroll.mapper.DoctorMapper;
import com.neroll.mapper.TreatTypeMapper;
import com.neroll.mapper.UserMapper;
import com.neroll.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DoctorLevelMapper levelMapper;

    @Autowired
    private TreatTypeMapper typeMapper;

    private boolean checkLevelId(Integer id) {
        List<DoctorLevel> levelIdList =levelMapper.getAllDoctorLevel();
        return levelIdList.stream().anyMatch(o -> o.getId().equals(id));
    }

    private boolean checkTreatTypeId(Integer id) {
        List<TreatType> types = typeMapper.getAllTreatType();
        return types.stream().anyMatch(o -> o.getId().equals(id));
    }

    public Result<PageInfo<DoctorVo>> searchDoctorByLevel(Integer pageNumber, Integer pageSize, String level) {
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;

        String searchString = "%" + level + "%";

        List<DoctorVo> doctors = doctorMapper.findDoctorByLevelByPage(offset, count, searchString);
        if (doctors == null)
            return Result.error("查找失败");

        int total = doctorMapper.findDoctorCountByLevel(searchString);

//        DoctorInfo info = new DoctorInfo();
        PageInfo<DoctorVo> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(doctors);

        return Result.success("查找成功", info);
    }

    public Result<Doctor> addDoctor(Doctor doctor) {
        if (!checkLevelId(doctor.getLevelId()))
            return Result.error("医师级别错误");
        if (!checkTreatTypeId(doctor.getTypeId()))
            return Result.error("医师治疗类别错误");

        doctor.setCreateTime(LocalDateTime.now());
        doctor.setUpdateTime(LocalDateTime.now());

        User user = userMapper.getUserByAccountId(doctor.getAccountId());
        if (user == null)
            return Result.error("用户不存在");

        int line = doctorMapper.saveDoctor(doctor);
        if (line == 0)
            return Result.error("添加失败");
        return Result.success("添加成功");
    }

    public Result<Doctor> updateDoctor(Integer id, Doctor doctor) {
        if (!checkLevelId(doctor.getLevelId()))
            return Result.error("医师级别错误");
        if (!checkTreatTypeId(doctor.getTypeId()))
            return Result.error("医师治疗类别错误");

        User user = userMapper.getUserByAccountId(doctor.getAccountId());
        if (user == null)
            return Result.error("用户不存在");

        doctor.setId(id);
        doctor.setUpdateTime(LocalDateTime.now());

        int line = doctorMapper.updateDoctor(doctor);
        if (line == 0)
            return Result.error("医师不存在");
        return Result.success("修改成功");
    }

    public Result<Doctor> deleteDoctorById(Integer id) {
        int line = doctorMapper.deleteDoctorById(id);
        if (line == 0)
            return Result.error("医师不存在");
        return Result.success("删除成功");
    }

    public Result getBarData() {
        List<BarDataVO> barDataList = doctorMapper.getBarData();

        List<String> levList = new ArrayList<>();
        List<Integer> countList = new ArrayList<>();

        if (barDataList != null && barDataList.size() > 0) {
            for (BarDataVO barData : barDataList) {
                levList.add(barData.getLev());
                countList.add(barData.getCount());
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("level", levList);
        map.put("count", countList);

        return new Result(200, "数据加载成功", map);
    }

    public Result getPieData() {
        List<PieDataVO> pieDataList = doctorMapper.getPieData();
        return new Result(200, "数据加载成功", pieDataList);

    }
}
