package sprintapp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import sprintapp.model.Sprint;
import sprintapp.model.State;
import sprintapp.model.Task;
import sprintapp.model.User;
import sprintapp.model.UserRole;
import sprintapp.repository.SprintRepository;
import sprintapp.repository.StateRepository;
import sprintapp.repository.TaskRepository;
import sprintapp.service.UserService;

@Component
public class TestData {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SprintRepository sprintRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
	public void init() {
		
		Sprint sprint1 = new Sprint();
		sprint1.setSprintName("Sprint 1");
		
		Sprint sprint2 = new Sprint();
		sprint2.setSprintName("Sprint 2");
		
		sprint1 = sprintRepository.save(sprint1);
		sprint2 = sprintRepository.save(sprint2);
		
		State newState = new State();
		newState.setStateName("New State");
		
		State inProgress = new State();
		inProgress.setStateName("In Progress");
		
		State finished = new State();
		finished.setStateName("Finished state");
		
		newState = stateRepository.save(newState);
		inProgress = stateRepository.save(inProgress);
		finished = stateRepository.save(finished);
		
		Task task1 = new Task();
		task1.setTaskName("Create rest service");
		task1.setTaskPoints(8);
		task1.setCharged("John");
		task1.setSprint(sprint1);
		task1.setState(inProgress);
		
		Task task2 = new Task();
		task2.setTaskName("Create a logo");
		task2.setTaskPoints(5);
		task2.setCharged("Anna");
		task2.setSprint(sprint1);
		task2.setState(finished);
		
		Task task3 = new Task();
		task3.setTaskName("Create home page");
		task3.setTaskPoints(5);
		task3.setCharged("Mark");
		task3.setSprint(sprint2);
		task3.setState(newState);
		
		taskRepository.save(task1);
		taskRepository.save(task2);
		taskRepository.save(task3);
		
		sprint1.setSprintPoints(13);
		sprintRepository.save(sprint1);
		
		sprint2.setSprintPoints(5);
		sprintRepository.save(sprint2);
		
		
//		List<User> users = new ArrayList<User>();
//		for (int i = 1; i <= 3; i++) {
//			User user = new User();
//			user.setUsername("user" + i);
//			user.setFirstName("First " + i);
//			user.setLastName("Last " + i);
//			user.setEmail("user"+i+"@mail.com");
//			user.setDateOfBirth(LocalDateTime.now().minusYears(20 + i));
//
//			String encodedPass = passwordEncoder.encode("pass"+i);
//			user.setPassword(encodedPass);
//
//			List<UserRole> roles = Arrays.asList(UserRole.values());
//			Random random = new Random();
//			user.setRole(roles.get(random.nextInt(3)));
//			
//			users.add(user);
//			userService.save(user);
//	}

	}
}
