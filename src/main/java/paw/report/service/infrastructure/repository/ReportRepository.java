package paw.report.service.infrastructure.repository;

import com.mongodb.client.FindIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.repository.IReportRepository;
import paw.report.service.infrastructure.entity.ReportEntity;
import paw.report.service.infrastructure.entity.ReportEntityMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReportRepository implements IReportRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Report create(Report report) {

        Report timestampedReport = new Report(report.getReportId(), report.getListingId(), report.getReason(), new Timestamp(System.currentTimeMillis()).toString(), report.getDescription());

        ReportEntity inserted = mongoTemplate.insert(
                ReportEntityMapper.fromDomainModel(timestampedReport),
                String.valueOf(report.getListingId())
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
        FindIterable<Document> reportsFetched = mongoTemplate.getCollection(String.valueOf(listingId)).find();
        reportsFetched.forEach();
    }
}
