package sprintapp.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import sprintapp.model.Task;
import sprintapp.web.dto.TaskDTO;

@Component
public class TaskToTaskDto implements Converter<Task, TaskDTO> {

	@Override
	public TaskDTO convert(Task task) {

		TaskDTO retVal = new TaskDTO();
		retVal.setId(task.getId());
		retVal.setTaskName(task.getTaskName());
		retVal.setTaskPoints(task.getTaskPoints());
		retVal.setCharged(task.getCharged());
		
		retVal.setSprintId(task.getSprint().getId());
		retVal.setSprintName(task.getSprint().getSprintName());
		
		retVal.setStateId(task.getState().getId());
		retVal.setStateName(task.getState().getStateName());
		
		return retVal;
	}
	
	public List<TaskDTO> convert(List<Task> tasks) {
		List<TaskDTO> ret = new ArrayList<>();
		
		for(Task t : tasks) {
			ret.add(convert(t));
		}
		
		return ret;
	}
	

}
