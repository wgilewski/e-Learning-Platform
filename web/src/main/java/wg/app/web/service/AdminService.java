package wg.app.web.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.model.message.MessageAdmin;
import wg.app.model.user.Admin;
import wg.app.persistence.repositories.AdminRepository;
import wg.app.persistence.repositories.MessageAdminRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminService
{


    private final AdminRepository adminRepository;
    private final MessageAdminRepository messageAdminRepository;

    public List<MessageAdmin> getAllMessages()
    {
        return messageAdminRepository.findAll();
    }

    public Optional<Admin> addAdmin(Admin admin)
    {
        return Optional.of(adminRepository.save(admin));
    }


}
