package org.training.issuetracker.ifaces;

import java.util.List;
import java.util.Map;
import org.training.issuetracker.model.beans.PropertyParameter;

public interface IssuePropertyDAO {
	List<PropertyParameter> getPropertyParameters(String propertyName);
	PropertyParameter getPropertyParameterById(String propertyName, int id);
	//Map <String, List <PropertyParameter>> getPropertiesMap();
    void updateProperty(int id, String propertyName, String newValue);
}
