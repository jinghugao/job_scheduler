package gaojh.job_scheduler.controller;

import gao.commons.utility.StoreUserThreadLocal;
import gaojh.job_scheduler.job.HelloJob;
import gaojh.job_scheduler.job.NewJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.web.bind.annotation.*;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
//import static org.quartz.core.jmx.SimpleTriggerSupport.newTrigger;
//import static org.quartz.core.jmx.CronTriggerSupport.newTrigger;

@RestController
public class JobSchedulerController {

    @RequestMapping("/test")
    public String getString() {
        return StoreUserThreadLocal.userThreadLocal.get().toString();
    }

    @ResponseBody
    @RequestMapping(value = "/addjob", method = RequestMethod.POST)
    public void addjob(@RequestParam(value = "jobClassName") String jobClassName) throws Exception {
        setJob(jobClassName);
    }

    public static void setJob(String jobClassName) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();

        Scheduler sched = sf.getScheduler();

        // 启动调度器
        sched.start();

        switch (jobClassName) {
            case "HelloJob":
                JobDetail job = newJob(HelloJob.class).withIdentity("HelloJob", "group1").build();
                Trigger trigger = newTrigger().withIdentity("HelloJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job, trigger);
                break;

            case "NewJob":
                JobDetail job2 = newJob(NewJob.class).withIdentity("NewJob", "group1").build();
                Trigger trigger2 = newTrigger().withIdentity("NewJob", "group1").startNow().withSchedule(simpleSchedule()
                        .withIntervalInSeconds(10)
                        .repeatForever()).build();
                sched.scheduleJob(job2, trigger2);
                break;
            default:
                break;
        }
    }
}
