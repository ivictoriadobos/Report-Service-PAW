package paw.report.service.infrastructure.mongodbconfig;

import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.stereotype.Component;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;

@Component
public class MongoCodecHelper {

    public CodecRegistry pojoCodecRegistry() {
        return fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), fromProviders(PojoCodecProvider.builder().automatic(true).build()));
    }

}
