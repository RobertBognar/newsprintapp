package sprintapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sprintapp.model.Sprint;
import sprintapp.service.SprintService;
import sprintapp.support.SprintToSprintDto;
import sprintapp.web.dto.SprintDTO;

@RestController
@RequestMapping("/api/sprints")
public class ApiSprintController {
	
	@Autowired
	private SprintService sprintService;
	
	@Autowired
	private SprintToSprintDto toDto;
	
	@GetMapping
	public ResponseEntity<List<SprintDTO>> getAll(@RequestParam(required = false) String sprintName) {
		List<Sprint> sprints = sprintService.findAll();
		return new ResponseEntity<>(toDto.convert(sprints), HttpStatus.OK);
	}

}
