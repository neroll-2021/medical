package com.neroll.service;

import com.neroll.mapper.DoctorMapper;
import com.neroll.pojo.Doctor;
import com.neroll.pojo.DoctorInfo;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorMapper mapper;

    public Result<DoctorInfo> searchDoctorByLevel(Integer pageNumber, Integer pageSize, String level) {
        int offset = (pageNumber - 1) * pageSize;
        int count = pageSize;

        String searchString = "%" + level + "%";

        List<Doctor> doctors = mapper.findDoctorByLevelByPage(offset, count, searchString);
        if (doctors == null)
            return Result.error("查找失败");

        int total = mapper.findDoctorCountByLevel(searchString);

        DoctorInfo info = new DoctorInfo();
        info.setTotal(total);
        info.setList(doctors);

        return Result.success("查找成功", info);

    }
}
