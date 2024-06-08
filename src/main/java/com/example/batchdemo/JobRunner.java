package com.example.batchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JobRunner implements ApplicationRunner {

    private final Job mejob;
    private final JobLauncher launcher;

    public JobRunner(Job mejob, JobLauncher launcher) {
        this.mejob = mejob;
        this.launcher = launcher;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        launcher.run(mejob, new JobParametersBuilder().addString("name", "user1").toJobParameters());
    }
}
