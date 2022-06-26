package com.marincek.batch;

import io.nflow.engine.workflow.curated.State;
import io.nflow.engine.workflow.definition.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskWorkflowDefinition extends WorkflowDefinition {

    public static final String TYPE = "task_processing_workflow";
    private static final String TASK_VAR = "task";
    private static final State PENDING = new State("pending", WorkflowStateType.start, "Pending state");
    private static final State PROCESSING = new State("processing", WorkflowStateType.normal, "Processing state");
    private static final State DONE = new State("done", WorkflowStateType.end, "Done state");
    private static final State ERROR = new State("error", WorkflowStateType.manual, "Error state");

    public TaskWorkflowDefinition() {
        super(TYPE, PENDING, ERROR);

        permit(PENDING, PROCESSING);
        permit(PROCESSING, DONE);
    }

    public NextAction pending(StateExecution execution) {
        if(StringUtils.isEmpty(execution.getBusinessKey())) {
            return NextAction.moveToState(ERROR, "Moving to error state, record business key is empty");
        }

        Task task = new Task(execution.getBusinessKey());
        execution.setVariable(TASK_VAR, task);
        return NextAction.moveToState(PROCESSING, "Moving to processing state");
    }

    public NextAction processing(StateExecution execution, @StateVar(value=TASK_VAR) Task task) {

        task.process(task);

        log.debug("Processing done for task {}", task);
        return NextAction.moveToState(DONE, "Moving to done state");
    }

}