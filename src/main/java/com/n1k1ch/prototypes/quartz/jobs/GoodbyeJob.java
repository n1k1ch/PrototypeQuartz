package com.n1k1ch.prototypes.quartz.jobs;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Locale;
import java.util.logging.Level;

/**
 * Created by n1k1ch on 24.09.2014.
 */
public class GoodbyeJob implements Job {

    private static final Logger log = Logger.getLogger(GoodbyeJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info(String.format(Locale.getDefault(), "Goodbye! Next goodbye at %s", context.getNextFireTime()));
    }
}
