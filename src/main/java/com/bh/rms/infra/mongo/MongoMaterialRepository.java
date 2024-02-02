package com.bh.rms.infra.mongo;

import com.bh.rms.domain.aggregate.material.Material;
import com.bh.rms.domain.aggregate.material.MaterialRepository;
import com.bh.rms.domain.aggregate.material.exception.MaterialNotFoundException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import lombok.extern.slf4j.Slf4j;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

@Profile("mongo")
@Slf4j
@Repository
public class MongoMaterialRepository implements MaterialRepository {
    private final MongoCollection<MongoMaterialDocument> materialMongoCollection;

    public MongoMaterialRepository(
            MongoCollection<MongoMaterialDocument> materialMongoCollection
    ) {
        this.materialMongoCollection = materialMongoCollection;
    }

    @Override
    public void create(Material material) {
        MongoMaterialDocument materialDocument = MongoMaterialDocument.convertForCreate(material);

        InsertOneResult result = materialMongoCollection.insertOne(materialDocument);
        if(!result.wasAcknowledged()) {
            throw new RuntimeException("insertOne was not acknowledged.");
        }
        if(result.getInsertedId() == null) {
            throw new RuntimeException("insertedId is null");
        }

        material.setId(materialDocument.getObjectId().toHexString());
    }

    @Override
    public void update(Material material) throws MaterialNotFoundException {
        MongoMaterialDocument materialDocument = MongoMaterialDocument.convertForUpdate(material);

        Bson filter = eq("_id", new ObjectId(material.getId()));
        UpdateResult result = materialMongoCollection.replaceOne(filter, materialDocument);

        if(!result.wasAcknowledged()) {
            throw new RuntimeException("replaceOne was not acknowledged");
        }
        if(result.getModifiedCount() != 1) {
            throw new RuntimeException("replaceOne was ignored.");
        }
    }

    @Override
    public void delete(String materialId) throws MaterialNotFoundException {
        Bson filter = eq("_id", materialId);
        DeleteResult result = materialMongoCollection.deleteOne(filter);

        if(!result.wasAcknowledged()) {
            throw new RuntimeException("deleteOne was not acknowledged.");
        }
        if(result.getDeletedCount() != 1) {
            log.warn("deleted count is not 1.");
        }
    }

    @Override
    public Material findById(String materialId) throws MaterialNotFoundException {
        Bson filter = eq("_id", new ObjectId(materialId));

        MongoMaterialDocument document = materialMongoCollection.find(filter)
                .first();
        if(document == null) {
            throw new MaterialNotFoundException();
        }
        return document.getMaterialWithObjectId();
    }

    @Override
    public List<Material> findByIds(List<String> materialIds) {
        List<ObjectId> objectIds = materialIds.stream()
                .map(ObjectId::new).toList();

        Bson filter = in("_id", objectIds);
        return materialMongoCollection.find(filter)
                .map(MongoMaterialDocument::getMaterialWithObjectId)
                .into(new ArrayList<>());
    }

    @Override
    public List<Material> findAll() {
        return materialMongoCollection.find()
                .limit(1000)
                .map(MongoMaterialDocument::getMaterialWithObjectId)
                .into(new ArrayList<>());
    }

    @Override
    public boolean existByIds(List<String> materialIds) {
        List<ObjectId> objectIds = materialIds.stream()
                .map(ObjectId::new).toList();

        Bson filter = in("_id", objectIds);
        return materialMongoCollection.countDocuments(filter) == materialIds.size();
    }
}
