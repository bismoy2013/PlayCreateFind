package models;

import java.util.ArrayList;
import java.util.List;
import org.mongodb.morphia.Key;
import controllers.MorphiaObject;

public abstract class BaseEntity {

	public List<? extends BaseEntity> all() {
		if (MorphiaObject.datastore != null) {
			return MorphiaObject.datastore.find(this.getClass()).asList();
		} else {
			return new ArrayList<BaseEntity>();
		}
	}

	public Key<BaseEntity> create(BaseEntity group) {
		return MorphiaObject.datastore.save(group);
	}
	
}
