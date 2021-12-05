package paw.report.service.api.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import paw.report.service.domain.model.Report;

public class ReportData {
    @JsonProperty
    private long listingId;

    @JsonProperty
    private String reason;

    @JsonProperty
    private String timestamp;

    @JsonProperty
    private String description;

    public ReportData(Report report) {
        this.listingId = report.getListingId();
        this.reason = report.getReason().name();
        this.timestamp = report.getTimestamp();
        this.description = report.getDescription();
    }
}
