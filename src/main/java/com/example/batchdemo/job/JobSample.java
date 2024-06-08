package com.example.batchdemo.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class JobSample {

//    @Bean
    public Job mejob(JobRepository jobRepository, Step step3) {
        return new JobBuilder("job3", jobRepository).start(step3).build();
    }

//    @Bean
    public Step step3(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step3", jobRepository).tasklet(tasklet3(), transactionManager).build();
    }

    private Tasklet tasklet3() {
        return (contribution, chunkContext) -> {
            System.err.println("step3 executed");
            return RepeatStatus.FINISHED;
        };
    }
}
