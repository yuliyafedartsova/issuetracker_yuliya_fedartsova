package org.training.issuetracker.ifaces;

import java.util.List;

import org.training.issuetracker.model.beans.PropertyParameter;

public interface PropertyDAO {
	List<PropertyParameter> getStatuses();
	PropertyParameter getStatusById(int id);
	List<PropertyParameter> getTypes();
	PropertyParameter getTypeById(int id);
	List<PropertyParameter> getPriorities();
	PropertyParameter getPriorityById(int id);
	List<PropertyParameter> getResolutions();
	PropertyParameter getResolutionById(int id);
	List<PropertyParameter> getRoles();
	PropertyParameter getRoleById(int id);
}
