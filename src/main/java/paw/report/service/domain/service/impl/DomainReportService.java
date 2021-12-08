package paw.report.service.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import paw.report.service.domain.exception.InvalidParameteresException;
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
        // should return 404 when listingId not found as a collection in repo
        return reportRepository.getAllByListingId(listingId);
    }

    @Override
    public List<Report> getAllReportsByReason(ReportReason reason) {
        return null;
    }

    @Override
    public List<Report> getALlReportsFromTimestamp(String timestamp) {
        return reportRepository.getAllFromTimestamp(timestamp);
    }

    @Override
    public List<Report> getAllByListingIdAndReason(long listingId, ReportReason reason) throws InvalidParameteresException {
        if (reason != ReportReason.UNKNOWN)
        {
            return reportRepository.getAllByListingIdAndReason(listingId, reason);
        }

        else throw new InvalidParameteresException();
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
    public List<Report> getAllReportsByReasonAndDay(ReportReason reason, String date) throws InvalidParameteresException {
        if(reason !=  ReportReason.UNKNOWN)
        {
            return reportRepository.getAllByReasonAndDate(reason, date);
        }

        else throw new InvalidParameteresException();
    }

    @Override
    public void banUser(String username) {

    }
}
