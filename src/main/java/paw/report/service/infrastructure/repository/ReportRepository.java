package paw.report.service.infrastructure.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.model.ReportReason;
import paw.report.service.domain.repository.IReportRepository;
import paw.report.service.infrastructure.entity.ReportEntity;
import paw.report.service.infrastructure.entity.ReportEntityMapper;
import paw.report.service.infrastructure.mongodbconfig.MongoCodecHelper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;

public class ReportRepository implements IReportRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoCodecHelper mongoCodecHelper;

    @Override
    public Report create(Report report) {
        Report timestampedReport = new Report(report.getReportId(), report.getListingId(), report.getReason(), new Timestamp(System.currentTimeMillis()).toString(), report.getDescription());

        ReportEntity inserted = mongoTemplate.insert(
                ReportEntityMapper.fromDomainModel(timestampedReport),
                valueOf(report.getListingId())
        );

        return ReportEntityMapper.toDomainModel(inserted);
    }

    @Override
    public List<Report> getAll() {

        List<Report> reports = new ArrayList<Report>();

        Set<String> collectionNames = mongoTemplate.getCollectionNames();

        mongoTemplate.getCollectionNames()
                    .stream()
                    .forEach(
                            collectionName -> reports.addAll(
                                    ReportEntityMapper.toDomainModel(
                                            mongoTemplate.findAll(
                                                    ReportEntity.class, collectionName
                                            )
                                    )
                            )
                    );

        return reports;
    }

    @Override
    public List<Report> getAllByListingId(long listingId) {

        List<Report> toBeReturned = new ArrayList<Report>();
        FindIterable<ReportEntity> reportsFetched;

        reportsFetched = mongoTemplate.getCollection(valueOf(listingId)).withCodecRegistry(mongoCodecHelper.pojoCodecRegistry()).find(ReportEntity.class);
        reportsFetched.forEach(reportEntity -> toBeReturned.add(ReportEntityMapper.toDomainModel(reportEntity)));

        return toBeReturned;
    }

    @Override
    public List<Report> getAllByListingIdAndReason(long listingId, ReportReason reason) {

        List<Report> toBeReturned = new ArrayList<Report>();
        List<ReportEntity> reportsFetched;

        reportsFetched = mongoTemplate.find(new Query(Criteria.where("reason").is(reason)), ReportEntity.class, String.valueOf(listingId));

        reportsFetched.forEach(reportEntity -> toBeReturned.add(ReportEntityMapper.toDomainModel(reportEntity)));

        return toBeReturned;
    }

    @Override
    public List<Report> getAllByReasonAndDate(ReportReason reason, String date) {

        List<Report> reports = new ArrayList<Report>();

        Set<String> collectionNames = mongoTemplate.getCollectionNames();

        Query byReasonAndDate = new Query(Criteria.where("reason").is(reason.name()).and("timestamp").regex(date));
        mongoTemplate.getCollectionNames()
                .stream()
                .forEach(
                        collectionName -> reports.addAll(
                                ReportEntityMapper.toDomainModel(
                                        mongoTemplate.find(byReasonAndDate, ReportEntity.class, collectionName)
                                )
                        )
                );

        return reports;
    }

    @Override
    public List<Report> getAllFromTimestamp(String timestamp) {
        List<Report> reports = new ArrayList<Report>();

        Set<String> collectionNames = mongoTemplate.getCollectionNames();

        Query fromDate = new Query(Criteria.where("timestamp").regex(timestamp));
        mongoTemplate.getCollectionNames()
                .stream()
                .forEach(
                        collectionName -> reports.addAll(
                                ReportEntityMapper.toDomainModel(
                                        mongoTemplate.find(fromDate, ReportEntity.class, collectionName)
                                )
                        )
                );

        return reports;
    }
}
