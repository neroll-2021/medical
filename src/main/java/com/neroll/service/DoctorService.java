package com.neroll.service;

import com.neroll.mapper.DoctorMapper;
import com.neroll.mapper.UserMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper doctorMapper;

    @Autowired
    private UserMapper userMapper;

    public Result<PageInfo<Doctor>> searchDoctorByLevel(Integer pageNumber, Integer pageSize, String level) {
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;

        String searchString = "%" + level + "%";

        List<Doctor> doctors = doctorMapper.findDoctorByLevelByPage(offset, count, searchString);
        if (doctors == null)
            return Result.error("查找失败");

        int total = doctorMapper.findDoctorCountByLevel(searchString);

//        DoctorInfo info = new DoctorInfo();
        PageInfo<Doctor> info = new PageInfo<>();
        info.setTotal(total);
        info.setList(doctors);

        return Result.success("查找成功", info);
    }

    public Result<Doctor> addDoctor(Doctor doctor) {
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
}
