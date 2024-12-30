package com.neroll.mapper;

import com.neroll.pojo.DisplayedMedicalPolicy;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class TestDisplayedMedicalPolicyMapper {
    @Autowired
    private MedicalPolicyMapper mapper;

    @Test
    public void testQuery() {
        List<DisplayedMedicalPolicy> policies = mapper.getMedicalPolicyByPage(0, 5, "");
        if (policies == null) {
            System.out.println("fail");
            return;
        }
        if (policies.isEmpty()) {
            System.out.println("empty");
            return;
        }
        for (DisplayedMedicalPolicy policy : policies) {
            System.out.println(policy);
        }
    }
}
