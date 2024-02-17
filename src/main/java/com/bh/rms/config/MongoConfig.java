package com.bh.rms.config;

import com.bh.rms.infra.mongo.MongoMaterialDocument;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Data;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Profile("mongo")
@EnableConfigurationProperties(MongoConfig.MongoProperties.class)
@Configuration
public class MongoConfig {
    @Data
    @ConfigurationProperties("mongo")
    public static class MongoProperties {
        private String connectionString;
        private String databaseName;
        private String materialCollectionName;
    }

    @Autowired
    private MongoProperties mongoProperties;

    @Bean(destroyMethod = "close")
    public MongoClient mongoClient() {
        return MongoClients.create(mongoProperties.getConnectionString());
    }

    @Bean
    public MongoDatabase mongoDatabase() {
        return mongoClient().getDatabase(mongoProperties.getDatabaseName());
    }

    @Bean
    public MongoCollection<MongoMaterialDocument> materialMongoCollection() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(
                getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider)
        );

        return mongoDatabase().withCodecRegistry(pojoCodecRegistry)
                .getCollection(mongoProperties.getMaterialCollectionName(), MongoMaterialDocument.class);
    }
}
