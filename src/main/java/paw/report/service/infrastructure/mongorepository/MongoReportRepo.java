package paw.report.service.infrastructure.mongorepository;

import org.springframework.data.mongodb.repository.MongoRepository;
import paw.report.service.infrastructure.entity.ReportEntity;

public interface MongoReportRepo extends MongoRepository<ReportEntity, String> {

}
