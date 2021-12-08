package paw.report.service.domain.service;

import paw.report.service.domain.exception.InvalidParameteresException;
import paw.report.service.domain.exception.InvalidReportException;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;

import java.util.List;

public interface IReportService {

    public List<Report> getAllReports();
    public List<Report> getAllByListingId(long listingId);
    public List<Report> getAllReportsByReason(ReportReason reason);
    public List<Report> getALlReportsFromTimestamp(String timestamp);
    public List<Report> getAllByListingIdAndReason(long listingId, ReportReason reason) throws InvalidParameteresException;
    public Report reportListing(Report report) throws InvalidReportException;
    public List<Report> getAllReportsByReasonAndDay(ReportReason reason, String date) throws InvalidParameteresException;
    public void banUser(String username); // usernameul luat din request; se arunca eveniment care e preluat de userservice si inactiveaza contul
}
