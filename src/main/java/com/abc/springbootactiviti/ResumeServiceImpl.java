package com.abc.springbootactiviti;

import com.abc.springbootactiviti.demo.ResumeService;
import org.springframework.stereotype.Service;

@Service("resumeService")
public class ResumeServiceImpl implements ResumeService {

    @Override
    public void showTask1() {
        System.out.println("#############################执行任务1");
    }

    @Override
    public void showTask2() {
        System.out.println("#############################执行任务2");
    }

    @Override
    public void showTask3() {
        System.out.println("#############################执行任务3");
    }
}
