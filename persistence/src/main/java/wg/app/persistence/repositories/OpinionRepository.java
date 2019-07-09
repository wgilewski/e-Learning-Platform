package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.user.Teacher.Opinion;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion,Long>
{
    void deleteById(Long id);
    List<Opinion> getAllByApprovedByAdminFalse();
}
