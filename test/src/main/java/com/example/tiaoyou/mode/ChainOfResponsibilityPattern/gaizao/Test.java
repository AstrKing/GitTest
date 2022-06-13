package com.example.tiaoyou.mode.ChainOfResponsibilityPattern.gaizao;

import lombok.extern.slf4j.Slf4j;

/**
 * @author WangYH
 * @date 2022/6/13
 *
 * 【优点】：减少了if else的使用，我们把比较判断的部门放在代码内部，只需要考虑我们的逻辑流程就可以
 * 【缺点】：每次传的值都不同，代码还是多啊，需要二次改造，
 *
 * 初步想法，既然每个流程都是说比较分数，我们把通用的部分提出来，搞个接口或者抽象类，每次就只管传
 * 下个流程的接口或抽象类的实现类就行
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        FirstPassHandler firstPassHandler = new FirstPassHandler();//第一关
        SecondPassHandler secondPassHandler = new SecondPassHandler();//第二关
        ThirdPassHandler thirdPassHandler = new ThirdPassHandler();//第三关

        firstPassHandler.setSecondPassHandler(secondPassHandler);//第一关的下一关是第二关
        secondPassHandler.setThirdPassHandler(thirdPassHandler);//第二关的下一关是第三关

        //说明：因为第三关是最后一关，因此没有下一关
        //开始调用第一关 每一个关卡是否进入下一关卡 在每个关卡中判断
        final int handler = firstPassHandler.handler();
        log.info("handler={}", handler);
    }
}
