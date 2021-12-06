package paw.report.service.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import paw.report.service.domain.exception.InvalidReportException;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;
import paw.report.service.domain.repository.IReportRepository;
import paw.report.service.domain.service.IReportService;

import java.util.List;

public class DomainReportService implements IReportService {

    @Autowired
    private IReportRepository reportRepository;


    @Override
    public List<Report> getAllReports() {

        return reportRepository.getAll();
    }

    @Override
    public List<Report> getAllByListingId(long listingId) {
        return reportRepository.
    }

    @Override
    public List<Report> getAllReportsByReason(ReportReason reason) {
        return null;
    }

    @Override
    public List<Report> getALlReportsFromDay(String timestamp) {
        return null;
    }

    @Override
    public List<Report> getAllByListingIdAndReason(long listingId, ReportReason reason) {
        return null;
    }

    @Override
    public Report reportListing(Report report) throws InvalidReportException {
        if(report.isValid())
        {
            return reportRepository.create(report);
        }

        throw new InvalidReportException();
    }

    @Override
    public void banUser(String username) {

    }
}
