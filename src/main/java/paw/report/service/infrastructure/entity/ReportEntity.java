package paw.report.service.infrastructure.entity;

import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document
public class ReportEntity {

    @BsonProperty("_id")
    @BsonId
    public ObjectId id;

    public long listingId;

    public String reason;

    public String timestamp;

    public String description;

//    @BsonCreator
//    public ReportEntity(@BsonId String id,
//                        @BsonProperty("listingId") long listingId,
//                        @BsonProperty("reason") String reason,
//                        @BsonProperty("timestamp") String timestamp,
//                        @BsonProperty("description") String description) {
//        this.id = id;
//        this.listingId = listingId;
//        this.reason = reason;
//        this.timestamp = timestamp;
//        this.description = description;
//    }

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

    public void setListingId(long listingId) {
        this.listingId = listingId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
