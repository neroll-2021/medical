package com.neroll.service;

import com.neroll.mapper.DoctorLevelMapper;
import com.neroll.pojo.DoctorLevel;
import com.neroll.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorLevelService {
    @Autowired
    private DoctorLevelMapper mapper;

    public Result<List<DoctorLevel>> getAllDoctorLevel() {
        List<DoctorLevel> levels = mapper.getAllDoctorLevel();
        if (levels == null)
            return Result.error("查询失败");
        return Result.success("查询成功", levels);
    }
}
