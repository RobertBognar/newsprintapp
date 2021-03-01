package sprintapp.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sprintapp.model.Task;
import sprintapp.service.TaskService;
import sprintapp.support.TaskToTaskDto;
import sprintapp.web.dto.TaskDTO;

@RestController
@RequestMapping(value = "/api/tasks")
public class ApiTaskController {
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private TaskToTaskDto toDTO;
	
	@RequestMapping(method = RequestMethod.GET)
	ResponseEntity<List<TaskDTO>> get(@RequestParam(value = "taskName", required = false) String taskName,
			@RequestParam(value = "idSprint", required = false) Long idSprint,
			@RequestParam(value = "pageNum", defaultValue = "0") int pageNum) {

		Page<Task> page = null;

		if (taskName != null || idSprint != null) {
			page = taskService.search(taskName, idSprint, pageNum);
		} else {
			page = taskService.findAll(pageNum);
		}

		HttpHeaders headers = new HttpHeaders();
		headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

		return new ResponseEntity<>(toDTO.convert(page.getContent()), headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	ResponseEntity<TaskDTO> getOne(@PathVariable Long id) {
		Optional<Task> task = taskService.findOne(id);
		if(!task.isPresent()) { 
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(task.get()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	ResponseEntity<TaskDTO> delete(@PathVariable Long id) {
		Task deleted = taskService.deleted(id);
		
		if(deleted == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
			return new ResponseEntity<>(toDTO.convert(deleted), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<TaskDTO> add(@Validated @RequestBody TaskDTO newDTO) {
		
		Task saved = taskService.save(newDTO);
		return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = "application/json")
	public ResponseEntity<TaskDTO> edit(@Validated @RequestBody TaskDTO taskDTO, @PathVariable Long id) {
		if(!id.equals(taskDTO.getId())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Task persisted = taskService.save(taskDTO);
		return new ResponseEntity<>(toDTO.convert(persisted), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/transition", method = RequestMethod.POST) 
	public ResponseEntity transition(@PathVariable Long id) {
		Task task = taskService.transition(id);
		if(task != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@ExceptionHandler(value = DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
