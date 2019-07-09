package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.message.MessageAdmin;

@Repository
public interface MessageAdminRepository extends JpaRepository<MessageAdmin,Long>
{

}
