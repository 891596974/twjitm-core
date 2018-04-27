package com.twjitm.core.spring;

import com.twjitm.core.service.dispatcher.IDispatcherService;
import com.twjitm.core.service.test.TestService;
import com.twjitm.core.service.user.UserService;

import javax.annotation.Resource;

/**
 * Created by �Ľ� on 2018/4/16.
 * ͨ��spring bean����
 */
public class SpringLoadManager {
    @Resource
    private TestService testService;
    @Resource
    private UserService userService;
    @Resource
    private IDispatcherService dispatcherService;



    public TestService getTestService() {
        return testService;
    }

    public UserService getUserService() {
        return userService;
    }

    public IDispatcherService getDispatcherService() {
        return dispatcherService;
    }

}
