package sprintapp.service;

import java.util.Optional;

import org.springframework.data.domain.Page;

import sprintapp.model.Task;
import sprintapp.web.dto.TaskDTO;

public interface TaskService {
	
	Page<Task> search(String taskName, Long idSprint, int pageNum);
	Page<Task> findAll(int page);
	Optional<Task> findOne(Long id);
	Task save(TaskDTO task);
	Task deleted(Long id);
	Task transition(Long id);

}
