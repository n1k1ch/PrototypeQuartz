package com.n1k1ch.prototypes.quartz.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Locale;

/**
 * Created by n1k1ch on 24.09.2014.
 */
public class HelloJob implements Job {
    private static final Logger log = Logger.getLogger(HelloJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info(String.format(Locale.getDefault(), "Hello. Next hello at %s", context.getNextFireTime()));
    }
}
