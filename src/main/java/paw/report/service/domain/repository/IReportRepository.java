package paw.report.service.domain.repository;

import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;

import java.util.List;

public interface IReportRepository {

    public Report create(Report report);
    public List<Report> getAll();
    public List<Report> getAllByListingId(long listingId);
    public List<Report> getAllByListingIdAndReason(long listingId, ReportReason reason);
    public List<Report> getAllByReasonAndDate(ReportReason reason, String date);
    public List<Report> getAllFromTimestamp(String timestamp);
}
