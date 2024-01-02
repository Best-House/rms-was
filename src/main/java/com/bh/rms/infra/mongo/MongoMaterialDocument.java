package com.bh.rms.infra.mongo;

import com.bh.rms.domain.aggregate.material.Material;
import lombok.Data;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.bson.types.ObjectId;

@Data
public class MongoMaterialDocument{
    @BsonId
    private ObjectId objectId;
    private Material material;

    public static MongoMaterialDocument convertForCreate(Material material) {
        MongoMaterialDocument document = new MongoMaterialDocument();
        document.setMaterial(material);
        return document;
    }

    public static MongoMaterialDocument convertForUpdate(Material material) {
        MongoMaterialDocument document = new MongoMaterialDocument();
//        document.setObjectId(new ObjectId(material.getId()));
        document.setMaterial(material);
        return document;
    }

    @BsonIgnore
    public Material getMaterialWithObjectId() {
        if(objectId != null) {
            material.setId(objectId.toHexString());
        }
        return material;
    }
}
