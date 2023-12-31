package com.roomfindingsystem.service.impl;


import com.roomfindingsystem.dto.ReportDto;
import com.roomfindingsystem.dto.ReportListDto;
import com.roomfindingsystem.entity.ReportEntity;
import com.roomfindingsystem.repository.ReportRepository;
import com.roomfindingsystem.service.ReportService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    private ReportRepository reportRepository;

    public ReportServiceImpl(ReportRepository reportRepository){
        super();
        this.reportRepository = reportRepository;
    }
    @Override
    public int countReports() {
        return reportRepository.countReports();
    }

    @Override
    public int countProcessingReports() {
        return reportRepository.countProcessingReports();
    }

    @Override
    public int countProcessedReports() {
        return reportRepository.countProcessedReports();
    }

    @Override
    public int countPendingReports() {
        return reportRepository.countPendingReports();
    }

    @Override

    public ReportEntity save(ReportEntity reportEntity) {
        return reportRepository.save(reportEntity);
    }

    @Override
    public List<ReportEntity> getReportEntityByUid(int houseid, int userid) {
        return reportRepository.getReportEntityByUid(houseid, userid);
    }

    @Override
    public void deleteByHouseIdAndMemberId(int houseid, int userid) {
        reportRepository.deleteByHouseIdAndMemberId(houseid, userid);}

    public List<ReportListDto> getAllReport() {
        return reportRepository.findAllReport();
    }

    @Override
    public int updateStatusProcessed(int id) {
        return reportRepository.updateStatusReportProcessed(id);
    }

    @Override
    public int updateStatusHandle(int id) {
        return reportRepository.updateStatusReportHandle(id);
    }

    @Override
    public int updateStatusWaiting(int id) {
        return reportRepository.updateStatusReportWaiting(id);

    }

    @Override
    public int updateSolve(LocalDate solveDate, int id) {
        return reportRepository.updateProcessedDate(solveDate,id);
    }

    @Override
    public Optional<ReportDto> getEmailForReply(String houseName) {
        return reportRepository.getEmailForRepLy(houseName);
    }
}
