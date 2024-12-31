package com.neroll.service;

import com.neroll.mapper.TreatTypeMapper;
import com.neroll.pojo.Result;
import com.neroll.pojo.TreatType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreatTypeService {
    @Autowired
    private TreatTypeMapper mapper;

    public Result<List<TreatType>> getAllTreatType() {
        List<TreatType> types = mapper.getAllTreatType();
        if (types == null)
            return Result.error("查询失败");
        return Result.success("查询成功", types);
    }
}
