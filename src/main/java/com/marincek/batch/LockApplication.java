package com.marincek.batch;

import io.nflow.engine.config.EngineConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@Import(EngineConfiguration.class)
public class LockApplication {

	public static void main(String[] args) {
		SpringApplication.run(LockApplication.class, args);
	}

}
