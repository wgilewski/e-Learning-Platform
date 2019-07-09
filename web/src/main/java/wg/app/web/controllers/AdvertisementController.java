package wg.app.web.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.advertisement.Advertisement;
import wg.app.web.service.AdvertisementService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/advertisement")
public class AdvertisementController
{

    private final AdvertisementService advertisementService;


    @PostMapping
    public ResponseEntity<Advertisement> addAdvertisement(RequestEntity<Advertisement> request)
    {

        return advertisementService
                .addOrUpdateAdvertisement(request.getBody())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<Advertisement> modifyAdvertisement(@RequestBody Advertisement advertisement)
    {
        return advertisementService
                .addOrUpdateAdvertisement(advertisement)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deleteAdvertisementById(@PathVariable Long id)
    {
        advertisementService.removeAdvertisement(id);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Advertisement>> getAllAdvertisements()
    {
        List<Advertisement> students = advertisementService.getAllAdvertisements();
        if (students == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/allSorted")
    public ResponseEntity<List<Advertisement>> getAllSortedAdvertisements()
    {
        List<Advertisement> students = advertisementService.getAllSortedByPremium();
        if (students == null)
        {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(students);
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<Advertisement> getAdvertisement(@PathVariable Long id)
    {
        return advertisementService.getOneAdvertisement(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/getByTag/{tag}")
    public ResponseEntity<List<Advertisement>> getByTag(@PathVariable String tag)
    {
        List<Advertisement> advertisements = advertisementService.getAllAdvertisementsByTag(tag);
        if (advertisements == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(advertisements);
    }

}
