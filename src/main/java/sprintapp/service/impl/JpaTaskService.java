package sprintapp.service.impl;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import sprintapp.model.Sprint;
import sprintapp.model.State;
import sprintapp.model.Task;
import sprintapp.repository.SprintRepository;
import sprintapp.repository.StateRepository;
import sprintapp.repository.TaskRepository;

import sprintapp.service.TaskService;
import sprintapp.support.TaskDtoToTask;
import sprintapp.web.dto.TaskDTO;

@Service
@Transactional
public class JpaTaskService implements TaskService {
	
	@Autowired
	private TaskDtoToTask toEntity;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private StateRepository stateRepository;

	@Override
	public Page<Task> search(String taskName, Long idSprint, int pageNum) {

			if(taskName != null) {
				taskName = "%" + taskName + "%";
			}
			
			//return taskRepository.search(taskName, idSprint, PageRequest.of(pageNum, 5));
			return null;
	}

	@Override
	public Page<Task> findAll(int page) {
		return taskRepository.findAll(PageRequest.of(page, 5));
	}

	@Override
	public Optional<Task> findOne(Long id) {
		return taskRepository.findById(id);
	}

	@Override
	public Task save(TaskDTO taskDTO) {

			Long id = taskDTO.getId();
			
			Integer oldTaskPointNumber = 0;
			
			if(id == null) {
				Optional<Task>  oldTaskOptional = findOne(id);
				if(oldTaskOptional.isPresent()) {
					Task oldTask = oldTaskOptional.get();
					oldTaskPointNumber = oldTask.getTaskPoints();
				}
			}
			
			Task task = toEntity.convert(taskDTO);
			if(task.getId() == null) {
				State newState = stateRepository.findById(1L).get();
				task.setState(newState);
			}
			
			Sprint sprint = task.getSprint();
			
			Integer basicPointNumber = sprint.getSprintPoints() - oldTaskPointNumber;
			Integer newPointNumber = basicPointNumber + task.getTaskPoints();
			sprint.setSprintPoints(newPointNumber);
			
			Task savedTask = taskRepository.save(task);
			sprintRepository.save(sprint);
			return savedTask;
	}

	@Override
	public Task deleted(Long id) {
		Optional<Task> taskOptional = taskRepository.findById(id);
		if(taskOptional.isPresent()) {
			Task task = taskOptional.get();
			
			Sprint sprint = task.getSprint();
			Integer newPointNumber = sprint.getSprintPoints() - task.getTaskPoints();
			sprint.setSprintPoints(newPointNumber);
			sprintRepository.save(sprint);
			
			taskRepository.deleteById(id);
			return task;
		}
		return null;
	}

	@Override
	public Task transition(Long id) {

		Task task = taskRepository.getOne(id);
		if(task != null) {
			State currentState = task.getState();
			if(currentState.getId().equals(1L)) {
				State inProgress = stateRepository.getOne(2L);
				task.setState(inProgress);
			} else if(currentState.getId().equals(2L)) {
				State finished = stateRepository.getOne(3L);
				task.setState(finished);
			}
			
			return taskRepository.save(task);
		}
		return null;
	}
	
	

}
