package com.marincek.batch;

import io.nflow.engine.service.WorkflowInstanceService;
import io.nflow.engine.workflow.instance.WorkflowInstanceFactory;
import lombok.RequiredArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class MessageListenerMock {

    private final WorkflowInstanceService workflowInstances;
    private final WorkflowInstanceFactory workflowInstanceFactory;

    @EventListener(ApplicationStartedEvent.class)
    public void run() {
        // simulate message received with 200 task IDs
        IntStream.range(0, 200)
                .mapToObj(operand -> UUID.randomUUID().toString())
                .forEach(this::createWorkflow);
    }

    private void createWorkflow(String id){
        workflowInstances.insertWorkflowInstance(workflowInstanceFactory.newWorkflowInstanceBuilder()
                .setType(TaskWorkflowDefinition.TYPE)
                .setCreated(DateTime.now(DateTimeZone.UTC))
                .setRetries(5)
                .setExternalId(id)
                .setBusinessKey(id)
                .build());
    }
}
