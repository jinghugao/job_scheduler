package gaojh.job_scheduler.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class NewJob implements BaseJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(NewJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LOGGER.error("New Job执行时间: " + LocalDateTime.now());
    }
}
