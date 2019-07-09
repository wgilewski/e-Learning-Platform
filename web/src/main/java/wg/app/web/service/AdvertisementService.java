package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.advertisement.Advertisement;
import wg.app.model.course.Subject;
import wg.app.model.exceptions.MyException;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.AdvertisementRepository;
import wg.app.persistence.repositories.TeacherRepository;


import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class AdvertisementService
{

    private final TeacherRepository teacherRepository;
    private final AdvertisementRepository advertisementRepository;


    public Optional<Advertisement> addOrUpdateAdvertisement(Advertisement advertisement) {

        //do zalogowane nauczyciela;

        Optional<Teacher> teacher = teacherRepository.findById(Long.valueOf(2));

        if (teacher.isPresent())
        {
            advertisement.setTeacher(teacher.get());
        } else throw new MyException("THERE IS NO TEACHER WITH SUCH ID IN DATABASE");

        return Optional.of(advertisementRepository.save(advertisement));
    }

    public void removeAdvertisement(Long id )
    {
        advertisementRepository.deleteById(id);
    }

    public Optional<Advertisement> getOneAdvertisement(Long advertisementId)
    {

        Optional<Teacher> teacher = teacherRepository.findAll()
                .stream()
                .filter(teacher1 -> teacher1.getAdvertisement().getId() == advertisementId)
                .findFirst();

        if (teacher.isPresent())
        {
            return Optional.of(teacher.get().getAdvertisement());
        }
        else throw new MyException("THERE IS NO TEACHER WITH SUCH ID IN DATABASE");
    }

    public List<Advertisement> getAllAdvertisements()
    {
        return advertisementRepository.findAll();
    }

    public List<Advertisement> getAllSortedByPremium()
    {
        return advertisementRepository.findAll()
                .stream()
                .filter(Advertisement::isPremium)
                .sorted(Comparator.comparing(Advertisement::getPremiumEnds).reversed())
                .collect(Collectors.toList());

    }

    public List<Advertisement> getAllAdvertisementsByTag(String tag)
    {

        return teacherRepository.findAll()
                .stream()
                .filter(teacher -> teacher.getSubjects().contains(Subject.valueOf(tag.toUpperCase())) && teacher.getAdvertisement().isPremium() == true)
                .map(teacher -> teacher.getAdvertisement())
                .sorted(Comparator.comparing(Advertisement::getPremiumEnds).reversed())
                .collect(Collectors.toList());
    }

}
