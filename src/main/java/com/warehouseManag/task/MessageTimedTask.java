package com.warehouseManag.task;

import com.warehouseManag.entity.Message;
import com.warehouseManag.service.MessageService;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@EnableAsync
public class MessageTimedTask {
    @Resource
    private MessageService messageService;

    @Scheduled(cron ="0 * * * * *" )
    public void run(){
        // 获取当前时间
        LocalDateTime currentTime = LocalDateTime.now();
        // 计算三小时前的时间
        LocalDateTime threeHoursAgo = currentTime.minusHours(3);
        Date date = Date.from(threeHoursAgo.atZone(ZoneId.systemDefault()).toInstant());
        List<Message> rs = messageService.selectOvertime(date);
        for (Message message:rs){
            String identity =message.getIdentity();
            String messageBody = message.getContent();
            // 发送短信给未读消息的用户（伪代码）
            System.out.println("sendSMS(identity, messageBody)");
        }
    }
}
