package sprintapp.web.dto;

public class SprintDTO {
	
	private Long id;
	private String sprintName;
	private Integer sprintPoints;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSprintName() {
		return sprintName;
	}
	public void setSprintName(String sprintName) {
		this.sprintName = sprintName;
	}
	public Integer getSprintPoints() {
		return sprintPoints;
	}
	public void setSprintPoints(Integer sprintPoints) {
		this.sprintPoints = sprintPoints;
	}
	
	

}
