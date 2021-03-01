package sprintapp.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import sprintapp.model.State;
import sprintapp.service.StateService;
import sprintapp.support.StateToStateDto;
import sprintapp.web.dto.StateDTO;

@RestController
@RequestMapping("api/states")
public class ApiStateController {
	
	@Autowired
	private StateService stateService;
	
	@Autowired
	private StateToStateDto toDto;
	
	@GetMapping
	public ResponseEntity<List<StateDTO>> getAll(@RequestParam(required = false) String stateName) {
		List<State> states = stateService.findAll();
		return new ResponseEntity<>(toDto.convert(states), HttpStatus.OK);
	}

}
