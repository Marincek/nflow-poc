package com.marincek.batch;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Slf4j
public class Task {

    private String uuid;

    public void process(Task task) {
        log.debug("Processing stared for : " + task.getUuid());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.debug("Processing done for : " + task.getUuid());
    }

}
