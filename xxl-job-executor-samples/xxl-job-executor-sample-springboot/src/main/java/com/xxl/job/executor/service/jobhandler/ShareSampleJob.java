package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author: ZhengTianLiang
 * @date: 2022/05/27 17:13
 * @desc:
 */

@Component
public class ShareSampleJob {

    private static Logger logger = LoggerFactory.getLogger(ShareSampleJob.class);

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/27 17:13
     * @desc: 测试1
     */
    @XxlJob("shareJobOne")
    public void test1() {
        // 调度日志-操作-执行日志
        XxlJobHelper.log("shareJobOne start ... ");
        // 控制台
        logger.info("shareJobOne test1 start ..");
    }
}
