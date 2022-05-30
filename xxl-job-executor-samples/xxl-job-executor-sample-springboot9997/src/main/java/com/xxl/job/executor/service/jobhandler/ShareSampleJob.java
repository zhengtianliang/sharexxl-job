package com.xxl.job.executor.service.jobhandler;

import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    @XxlJob("shareJobOneCopy")
    public void test1() {
        // 调度日志-操作-执行日志
        XxlJobHelper.log("shareJobOneCopy copy start ... ");
        // 控制台
        logger.info("shareJobOneCopy copy test1 start ..");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/29 21:24
     * @desc: 测试1
     */
    @XxlJob("shareJobOne")
    public void test2() {
        // 调度日志-操作-执行日志
        XxlJobHelper.log("shareJobOne start ... ");
        // 控制台
        logger.info("shareJobOne test1 start ..");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/30 21:50
     * @desc: 测试1
     */
    @XxlJob("shardingJob")
    public void shardingJob(){
        // 分片参数
        int shardIndex = XxlJobHelper.getShardIndex();
        int shardTotal = XxlJobHelper.getShardTotal();
        logger.info("分片参数：当前分片序号={},总分片数={}",shardIndex,shardTotal);

        List<Integer> datas = query();
        for (int i=0;i<datas.size();i++){
            Integer id = datas.get(i);
            if (id % shardTotal == shardIndex){
                System.out.println(String.format("当前片%s 开始处理%s 值 %s",shardIndex,id ,id%shardTotal));
            }
        }
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/30 21:52
     * @desc: 假设这是从库中查的动态的数据
     */
    private List<Integer> query(){
        List<Integer> dataList = new ArrayList<>();
        for (int i=0;i<100;i++){
            dataList.add(i+1);
        }
        return dataList;
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/30 22:59
     * @desc: 测试子任务_1
     */
    @XxlJob("subtaskOne")
    public void subtaskOne(){
        XxlJobHelper.log("subtaskOne start ... ");
        logger.info("subtaskOne start ..");
    }

    /**
     * @author: ZhengTianLiang
     * @date: 2022/05/30 22:59
     * @desc: 测试子任务_1
     */
    @XxlJob("subtaskTwo")
    public void subtaskTwo(){
        XxlJobHelper.log("subtaskTwo start ... ");
        logger.info("subtaskTwo start ..");
    }
}
