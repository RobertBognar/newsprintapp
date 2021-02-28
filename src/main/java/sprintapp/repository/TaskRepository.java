package sprintapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import sprintapp.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	//@Query("SELECT t FROM Task t WHERE" + 
	//		"(:taskName = NULL OR t.name LIKE :taskName) AND " + 
	//	"(:idSprint = NULL OR t.sprint.id = :idSprint)")
	//Page<Task> search(@Param("taskName") String taskName, @Param("idSprint") Long idSprint, Pageable pageable);
}
