package sprintapp.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.Sprint;
import sprintapp.model.State;
import sprintapp.model.Task;
import sprintapp.service.SprintService;
import sprintapp.service.StateService;
import sprintapp.service.TaskService;
import sprintapp.web.dto.TaskDTO;

@Component
public class TaskDtoToTask implements Converter<TaskDTO, Task>{
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private StateService stateService;

	@Override
	public Task convert(TaskDTO taskDTO) {

		Sprint sprint = null;
		if(taskDTO.getSprintId() != null) {
			sprint = sprintService.findOne(taskDTO.getSprintId()).get();
		}
		
		State state = null;
		if(taskDTO.getStateId() != null) {
			state = stateService.findOne(taskDTO.getStateId()).get();
		}
		
		if(sprint != null) {
			Long id = taskDTO.getId();
			Task task = id == null ? new Task() : taskService.findOne(id).get();
			
			if(task != null) {
				task.setId(taskDTO.getId());
				task.setTaskName(taskDTO.getTaskName());
				task.setTaskPoints(taskDTO.getTaskPoints());
				task.setCharged(taskDTO.getCharged());
				
				task.setSprint(sprint);
				
				if(state != null) {
					task.setState(state);
				}
			}
			return task;
		} else {
			throw new IllegalStateException("Trying to attach to non-existant entities");
		}
	}
	
	

}
