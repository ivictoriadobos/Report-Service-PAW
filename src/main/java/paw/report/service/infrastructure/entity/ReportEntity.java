package paw.report.service.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ReportEntity {

    @Id
    private String id;

    private long listingId;

    private String reason;

    private String timestamp;

    private String description;

    public ReportEntity(String id, long listingId, String reason, String timestamp, String description) {
        this.id = id;
        this.listingId = listingId;
        this.reason = reason;
        this.timestamp = timestamp;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public long getListingId() {
        return listingId;
    }

    public String getReason() {
        return reason;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDescription() {
        return description;
    }

}
