package com.example.demo.optional;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * @author WangYH
 * @date 2022/6/5
 */
public class OptionalTest {
    @Test
    public void test1() {
        Girl girl = new Girl();
        girl = null;
        // of方法不能为null
        //Optional<Girl> optionalGirl = Optional.of(girl);
        // empty是为null

        // 而ofNullable是可以为null的
        Optional<Girl> girlOptional = Optional.ofNullable(girl);
    }

    public String getGirlName(Boy boy) {
        return boy.getGirl().getName();
    }

    @Test
    public void test2(){
        Boy boy = null;
        String name = getGirlNameOptional(null);
        System.out.println(name);
    }

    // Optional方法
    public String getGirlNameOptional(Boy boy) {
        Optional<Boy> optionalBoy = Optional.ofNullable(boy);
        Boy boy1 = optionalBoy.orElse(new Boy(new Girl("迪丽热巴")));
        Girl girl = boy1.getGirl();
        Optional<Girl> girl1 = Optional.ofNullable(girl);

        Girl girl2 = girl1.orElse(new Girl("古力娜扎"));
        return girl2.getName();
    }

    // 优化后的getGirlName方法
    public String getGirlName1(Boy boy) {
        if(boy!=null){
            Girl girl = boy.getGirl();
            if(girl!=null){
                return girl.getName();
            }
        }
        return null;
    }
}
