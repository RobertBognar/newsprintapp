package sprintapp.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class TaskDTO {
	
	private Long id;
	
	@NotBlank
	@Length(max = 40)
	private String taskName;
	private String charged;
	@Min(value = 0)
	@Max(value = 20)
	private Integer taskPoints;
	
	private Long sprintId;
	private Long stateId;
	
	private String sprintName;
	private String stateName;
	
	public TaskDTO() {
		
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

	public Long getSprintId() {
		return sprintId;
	}

	public void setSprintId(Long sprintId) {
		this.sprintId = sprintId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getSprintName() {
		return sprintName;
	}

	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	
	

}
