package wg.app.web.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wg.app.model.message.MessageAdmin;
import wg.app.model.user.Admin;
import wg.app.model.user.Teacher.Opinion;
import wg.app.persistence.repositories.OpinionRepository;
import wg.app.web.service.AdminService;
import wg.app.web.service.DataManagement;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController
{
    private final AdminService adminService;
    private final DataManagement dataManagement;
    private final OpinionRepository opinionRepository;

    @PostMapping
    public ResponseEntity<Admin> addAdmin(RequestEntity<Admin> request)
    {
        return adminService
                .addAdmin(request.getBody())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/deleteData")
    public void deleteData()
    {
        dataManagement.deleteAllData();
    }

    @GetMapping("/init")
    public void initData()
    {
        dataManagement.init();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<MessageAdmin>> messages()
    {
        List<MessageAdmin> messageAdmins = adminService.getAllMessages();
        if (messageAdmins == null)
        {
            return ResponseEntity.notFound().build();
        }
        else
        {
            return ResponseEntity.ok(messageAdmins);
        }

    }

    @GetMapping("/checkOpinions")
    public ResponseEntity<List<Opinion>> opinionsToApprove()
    {
        List<Opinion> opinions = opinionRepository.getAllByApprovedByAdminFalse();

        if (opinions == null)
        {
            return ResponseEntity.notFound().build();
        }
        else return ResponseEntity.ok(opinions);
    }
}
