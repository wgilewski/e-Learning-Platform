package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.user.Teacher.Dashboard;

@Repository
public interface DashboardRepository extends JpaRepository<Dashboard,Long>
{

}
