package com.bh.rms.infra.mongo;

import com.bh.rms.config.AbstractProdIntegrationTest;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.InsertOneResult;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LearningMongoTest extends AbstractProdIntegrationTest {
    @Autowired
    MongoDatabase mongoDatabase;
    MongoCollection<SomeDocument> mongoCollection;

    @NoArgsConstructor
    @Data
    public static class SomeDocument {
        @BsonId
        private ObjectId id;
        private String data;

        public SomeDocument(String data) {
            this.data = data;
        }
    }

    @BeforeEach
    public void setup() {
        CodecProvider pojoCodecProvider = PojoCodecProvider.builder()
                .automatic(true)
                .build();
        CodecRegistry pojoCodecRegistry = fromRegistries(
                getDefaultCodecRegistry(),
                fromProviders(pojoCodecProvider)
        );

        mongoCollection = mongoDatabase.withCodecRegistry(pojoCodecRegistry)
                .getCollection("test", SomeDocument.class);
    }

    @Test
    public void test() {
        SomeDocument someDocument = new SomeDocument("test");
        InsertOneResult insertOneResult = mongoCollection.insertOne(someDocument);
        assertTrue(insertOneResult.wasAcknowledged());
        assertNotNull(insertOneResult.getInsertedId());

        Bson filter = eq("_id", someDocument.getId());
        SomeDocument foundedSomeDocument = mongoCollection.find(filter).first();
        assertNotNull(foundedSomeDocument);
    }
}