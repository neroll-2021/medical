package com.neroll.mapper;

import com.neroll.pojo.MedicalPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestMedicalPolicyMapper {
    @Autowired
    private MedicalPolicyMapper mapper;

    @Test
    public void testQuery() {
        List<MedicalPolicy> policies = mapper.getMedicalPolicyByPage(0, 5);
        if (policies == null) {
            System.out.println("fail");
            return;
        }
        if (policies.isEmpty()) {
            System.out.println("empty");
            return;
        }
        for (MedicalPolicy policy : policies) {
            System.out.println(policy);
        }
    }
}
