package sprintapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String taskName;
	
	@Column(nullable = false)
	private String charged;
	
	@Column
	private Integer taskPoints;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Sprint sprint;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private State state;
	
	public Task() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getCharged() {
		return charged;
	}

	public void setCharged(String charged) {
		this.charged = charged;
	}

	public Integer getTaskPoints() {
		return taskPoints;
	}

	public void setTaskPoints(Integer taskPoints) {
		this.taskPoints = taskPoints;
	}

	public Sprint getSprint() {
		return sprint;
	}

	public void setSprint(Sprint sprint) {
		this.sprint = sprint;
		if(!sprint.getTasks().contains(this)) {
			sprint.getTasks().add(this);
		}
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
		if(!state.getTasks().contains(this)) {
			state.getTasks().add(this);
		}
	}
	
	

}
