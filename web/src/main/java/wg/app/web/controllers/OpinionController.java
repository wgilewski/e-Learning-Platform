package wg.app.web.controllers;


import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wg.app.model.user.Teacher.Opinion;
import wg.app.web.service.OpinionService;

import java.util.List;

@RestController
@RequestMapping("/opinion")
public class OpinionController
{
    private OpinionService opinionService;
    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }



    @GetMapping("/{id}")
    public ResponseEntity<List<Opinion>> getAllOpinions(@PathVariable Long id)
    {
        List<Opinion> opinions = opinionService.getAllOpinionsByTeacherId(id).get();

        if (opinions == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(opinions);
    }

    @PostMapping("/add/{teacherId}")
    public ResponseEntity<Opinion> addOpinion(@PathVariable Long teacherId, RequestEntity<Opinion> request)
    {
        return opinionService
                .addOrUpdateOpinion(request.getBody(),teacherId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
