package wg.app.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.message.Invitation;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation,Long>
{
    List<Invitation> findAllByTeacherId(Long id);
}
