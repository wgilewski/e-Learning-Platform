package wg.app.advertisementTests;

import jdk.jfr.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import wg.app.model.advertisement.Advertisement;
import wg.app.model.course.Subject;
import wg.app.model.user.Teacher.Teacher;
import wg.app.persistence.repositories.AdvertisementRepository;
import wg.app.persistence.repositories.TeacherRepository;
import wg.app.web.service.AdvertisementService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@ExtendWith(SpringExtension.class)
public class AdvertisementServiceTests
{
    @TestConfiguration
    public static class MyTestConfiguration{

        @MockBean
        private TeacherRepository teacherRepository;


        @MockBean
        private AdvertisementRepository advertisementRepository;

        @Bean
        public AdvertisementService advertisementService()
        {
            return new AdvertisementService(teacherRepository, advertisementRepository);
        }
    }


    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AdvertisementService advertisementService;


    @Test
    @DisplayName("get all advertisement")
    public void test1()
    {
        Mockito
                .when(advertisementRepository.findAll())
                .thenReturn(List.of(
                        Advertisement.builder().build(),
                        Advertisement.builder().premium(true).build(),
                        Advertisement.builder().build(),
                        Advertisement.builder().build()
                ));


        List<Advertisement> advertisements = advertisementService.getAll();
        Assertions.assertEquals(4,advertisements.size());
    }

    @Test
    @DisplayName("get one advertisement")
    public void test2()
    {
        Mockito
                .when(advertisementRepository.getOne(2L))
                .thenReturn(Advertisement.builder().id(2l).premium(true).build());

        Optional<Advertisement> advertisement = advertisementService.getOne(2l);
        Assertions.assertEquals(advertisement.isPresent(),true);
        Assertions.assertEquals(advertisement.get().isPremium(),true);
        Assertions.assertEquals(advertisement.get().getId(),2l);
    }

    @Test
    @DisplayName("get all sorted by premium and premium ends")
    public void  test3()
    {
        Mockito
                .when(advertisementRepository.findAll())
                .thenReturn(List.of(
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now()).teacher(Teacher.builder().points(41).build()).build(),
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now().plusDays(12)).teacher(Teacher.builder().points(22).build()).build(),
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now().plusDays(13)).teacher(Teacher.builder().points(21).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().points(12).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().points(16).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().points(11).build()).build()
                ));

        List<Advertisement> advertisements = advertisementService.getAllSorted();
        advertisements.forEach(System.out::println);

        Assertions.assertEquals(advertisements.size(),6);
        Assertions.assertEquals(advertisements.get(0).isPremium(),true);
        Assertions.assertEquals(advertisements.get(0).getTeacher().getPoints(),Integer.valueOf(21));

    }

    @Test
    @DisplayName("get all advertisements by tag")
    public void test4()
    {

        Mockito
                .when(advertisementRepository.findAll())
                .thenReturn(List.of(
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now().plusDays(12)).teacher(Teacher.builder().subjects(Set.of(Subject.JAVA)).points(22).build()).build(),
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now().plusDays(13)).teacher(Teacher.builder().subjects(Set.of(Subject.JAVA,Subject.BIOLOGY)).points(21).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().subjects(Set.of(Subject.JAVA)).points(12).build()).build(),
                        Advertisement.builder().premium(true).premiumEnds(LocalDate.now()).teacher(Teacher.builder().subjects(Set.of(Subject.JAVA)).points(41).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().subjects(Set.of(Subject.BIOLOGY)).points(16).build()).build(),
                        Advertisement.builder().premium(false).teacher(Teacher.builder().subjects(Set.of(Subject.JAVA)).points(11).build()).build()
                ));

            List<Advertisement> advertisements = advertisementService.getAllByTag("java");
            Assertions.assertEquals(advertisements.size(),5);
            Assertions.assertEquals(advertisements.get(0).getTeacher().getPoints(),Integer.valueOf(21));
            Assertions.assertEquals(advertisements.get(0).getTeacher().getSubjects().size(),2);

    }

}
