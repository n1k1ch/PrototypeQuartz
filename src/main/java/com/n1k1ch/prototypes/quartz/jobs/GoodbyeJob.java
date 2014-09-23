package com.n1k1ch.prototypes.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Locale;

/**
 * Created by n1k1ch on 24.09.2014.
 */
public class GoodbyeJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(String.format(Locale.getDefault(), "Goodbye! Next goodbye at %s", context.getNextFireTime()));
    }
}
