package paw.report.service.api.requests;

public class ReportListingRequest {
    private String reason;
    private String description;

    public ReportListingRequest(String reason, String description) {
        this.reason = reason;
        this.description = description;
    }

    public String getReason() {
        return reason;
    }

    public String getDescription() {
        return description;
    }
}
