package com.bbshop.app;

import com.bbshop.common.constant.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendApplicationTests {

    @Test
    void contextLoads() {
        String name = RoleEnum.ADMIN.name();
        System.out.println(name);
    }

}
