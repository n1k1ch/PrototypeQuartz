package com.n1k1ch.prototypes.quartz;

import com.n1k1ch.prototypes.quartz.jobs.GoodbyeJob;
import com.n1k1ch.prototypes.quartz.jobs.HelloJob;
import org.apache.log4j.BasicConfigurator;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by n1k1ch on 24.09.2014.
 */
public class Main {
    public static void main(String[] args) {
        BasicConfigurator.configure();

        Main main = new Main();
        //main.executeCron("1/10 * * * * ?");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2015);
        calendar.set(Calendar.MONTH, Calendar.JANUARY);
        calendar.set(Calendar.DAY_OF_MONTH, 31);
        main.executeCalendar(5, calendar.getTime());
    }

    public void executeCron(String expression) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
                    .withIdentity("JobName", "group1")
                    .build();

            CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("TriggerName", "group1")
                    .withSchedule(CronScheduleBuilder.cronSchedule(expression))
                    .build();

            scheduler.start();

            scheduler.scheduleJob(jobDetail, cronTrigger);

            System.out.println(String.format(Locale.getDefault(), "Cron created: Next execution at %s", cronTrigger.getNextFireTime()));

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    public void executeCalendar(Integer months, Date startDate) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();

        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            JobDetail jobDetail = JobBuilder.newJob(GoodbyeJob.class)
                    .withIdentity("GoodBye", "group2")
                    .build();

            CalendarIntervalTrigger calendarIntervalTrigger = TriggerBuilder.newTrigger()
                    .withIdentity("CalendarIntervalTrigger1", "group2")
                    .withSchedule(CalendarIntervalScheduleBuilder.calendarIntervalSchedule()
                                    .withIntervalInMonths(months)
                    )
                    .startAt(startDate)
                    .build();

            scheduler.start();

            scheduler.scheduleJob(jobDetail, calendarIntervalTrigger);

            System.out.println(String.format(Locale.getDefault(), "CalendarInterval created: Next execution at %s", calendarIntervalTrigger.getNextFireTime()));

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
