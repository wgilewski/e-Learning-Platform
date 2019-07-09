package wg.app.persistence.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wg.app.model.advertisement.Advertisement;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement,Long>
{
}
