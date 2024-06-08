package com.example.batchdemo.job;

import com.example.batchdemo.tasklet.CustomTasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JobSample2 {

    @Bean
    public Job mejob(JobRepository jobRepository, Step step1, Step step2) {
        return new JobBuilder("job1", jobRepository).start(step1)
                                                           .next(step2)
                                                           .build();
    }

    @Bean
    public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step1", jobRepository).tasklet(new CustomTasklet(), transactionManager).build();
    }

    private Tasklet tasklet1() {
        return (contribution, chunkContext) -> {
            System.err.println("step1 executed");
            return RepeatStatus.FINISHED;
        };
    }

    @Bean
    public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step2", jobRepository).tasklet(tasklet2(), transactionManager).build();
    }

    private Tasklet tasklet2() {
        return (contribution, chunkContext) -> {
            System.err.println("step2 executed");
            return RepeatStatus.FINISHED;
        };
    }
}
