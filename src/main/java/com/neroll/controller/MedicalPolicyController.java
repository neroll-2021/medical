package com.neroll.controller;

import com.neroll.pojo.DisplayedMedicalPolicy;
import com.neroll.pojo.MedicalPolicy;
import com.neroll.pojo.PageInfo;
import com.neroll.pojo.Result;
import com.neroll.service.MedicalPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/medical_policy")
public class MedicalPolicyController {
    @Autowired
    private MedicalPolicyService service;

    @GetMapping
    public Result<PageInfo<DisplayedMedicalPolicy>> getPolicyByPage(@RequestParam("pn") Integer pageNum,
                                                                    @RequestParam("size") Integer pageSize,
                                                                    @RequestParam(value = "keyword", defaultValue = "") String keyword) {
        if (pageNum == null)
            return Result.error("页码不能为空");
        if (pageSize == null)
            return Result.error("页大小不能为空");
        return service.getPolicyByPage(pageNum, pageSize, keyword);
    }

    @PostMapping
    public Result<MedicalPolicy> addPolicy(@RequestParam("city") String city, @RequestBody MedicalPolicy policy) {
        if (policy == null)
            return Result.error(" 医保政策数据不能为空");
        if (!StringUtils.hasText(policy.getTitle()))
            return Result.error("医保政策标题不能为空");
        if (!StringUtils.hasText(policy.getMessage()))
            return Result.error("医保政策内容不能为空");
        return service.addPolicy(city, policy);
    }

    @PutMapping("/{id}")
    public Result<MedicalPolicy> updatePolicy(@PathVariable Integer id, @RequestBody MedicalPolicy policy) {
        if (id == null)
            return Result.error("医保政策 id 不能为空");
        if (policy == null)
            return Result.error("医保政策数据不能为空");
        if (!StringUtils.hasText(policy.getTitle()))
            return Result.error("医保政策标题不能为空");
        if (!StringUtils.hasText(policy.getMessage()))
            return Result.error("医保政策内容不能为空");
        if (policy.getCityId() == null)
            return Result.error("城市 id 不能为空");
        return service.updatePolicy(id, policy);
    }

    @DeleteMapping("/{id}")
    public Result<MedicalPolicy> deletePolicy(@PathVariable Integer id) {
        if (id == null)
            return Result.error("医保政策 id 不能为空");
        return service.deletePolicyById(id);
    }
}
