package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.user.Admin;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long>
{
    Optional<Admin> getOneByUsername(String username);
}
