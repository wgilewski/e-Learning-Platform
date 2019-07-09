package wg.app.web.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import wg.app.persistence.repositories.DashboardRepository;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class DashboardService
{
    private final DashboardRepository dashboardRepository;



}
