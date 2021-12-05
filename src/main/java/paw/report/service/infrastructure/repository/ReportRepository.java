package paw.report.service.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import paw.report.service.domain.model.Report;
import paw.report.service.domain.repository.IReportRepository;
import paw.report.service.infrastructure.entity.ReportEntity;
import paw.report.service.infrastructure.entity.ReportEntityMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReportRepository implements IReportRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Report create(Report report) {

        ReportEntity inserted = mongoTemplate.insert(ReportEntityMapper.fromDomainModel(report), String.valueOf(report.getListingId()));
        return ReportEntityMapper.toDomainModel(inserted);
    }

    @Override
    public List<Report> getAll() {

        List<Report> reports = new ArrayList<Report>();
        mongoTemplate.getCollectionNames()
                    .stream()
                    .forEach(collectionName -> reports.addAll(
                                                ReportEntityMapper.toDomainModel(
                                                        mongoTemplate.findAll(ReportEntity.class, collectionName))
                                                )
                    );

        return reports;
    }
}
