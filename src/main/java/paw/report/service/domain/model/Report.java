package paw.report.service.domain.model;

import java.util.UUID;

public class Report {
    private final String reportId;
    private final long listingId;
    private final ReportReason reason;
    private final String timestamp;
    private final String description;

    public Report(String reportId, long listingId, ReportReason reason, String timestamp, String description) {
        this.reportId = reportId;
        this.listingId = listingId;
        this.reason = reason;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getReportId() {
        return reportId;
    }

    public long getListingId() {
        return listingId;
    }

    public ReportReason getReason() {
        return reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

    public boolean isValid()
    {
        if(reason==ReportReason.UNKNOWN)
        {
            return false;
        }

        if(listingId <= 0)
        {
            return false;
        }

        return true;
    }
}
