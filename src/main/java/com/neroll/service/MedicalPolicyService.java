package com.neroll.service;

import com.neroll.mapper.ChinaMapper;
import com.neroll.mapper.CityMapper;
import com.neroll.mapper.MedicalPolicyMapper;
import com.neroll.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin
@Service
public class MedicalPolicyService {
    @Autowired
    private CityMapper cityMapper;

    @Autowired
    private MedicalPolicyMapper policyMapper;

    @Autowired
    private ChinaMapper chinaMapper;

    public Result<PageInfo<DisplayedMedicalPolicy>> getPolicyByPage(Integer pageNum, Integer pageSize) {
        if (pageNum <= 0)
            return Result.error("页码错误");
        if (pageSize <= 0)
            return Result.error("页大小错误");

        int offset = (pageNum - 1) * pageSize;
        int count = pageSize;

        List<DisplayedMedicalPolicy> policies = policyMapper.getMedicalPolicyByPage(offset, count);
        if (policies == null)
            return Result.error("查询失败");
        PageInfo<DisplayedMedicalPolicy> info = new PageInfo<>();

        int total = policyMapper.getMedicalPolicyNum();

        info.setTotal(total);
        info.setList(policies);
        return Result.success("查询成功", info);
    }

    public Result<MedicalPolicy> addPolicy(String city, MedicalPolicy policy) {
        List<Region> regions = chinaMapper.getRegionsByName(city);
        if (regions == null)
            return Result.error("查询城市信息失败");
        if (regions.isEmpty())
            return Result.error("城市不存在");
        if (regions.size() != 1)
            return Result.error("城市重名（这怎么可能？）");

        City theCity = cityMapper.getCityByNumber(regions.get(0).getId());
        policy.setCityId(theCity.getId());

        policy.setCreateTime(LocalDateTime.now());
        policy.setUpdateTime(LocalDateTime.now());

        int line = policyMapper.saveMedicalPolicy(policy);
        if (line == 0)
            return Result.error("添加失败");
        return Result.success("添加成功");
    }

    public Result<MedicalPolicy> updatePolicy(Integer id, MedicalPolicy policy) {
        policy.setId(id);
        policy.setUpdateTime(LocalDateTime.now());

        City city = cityMapper.getCityById(policy.getCityId());
        if (city == null)
            return Result.error("城市不存在");

        int line = policyMapper.updateMedicalPolicy(policy);
        if (line == 0)
            return Result.error("医保政策不存在");

        return Result.success("修改成功");
    }

    public Result<MedicalPolicy> deletePolicyById(Integer id) {
        int line = policyMapper.deleteMedicalPolicyById(id);
        if (line == 0)
            return Result.error("医保政策不存在");
        return Result.success("删除成功");
    }
}
