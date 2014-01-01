package com.innovazions.jbm.view;

import java.util.List;

public abstract class GenericView<View, Entity> {

	public abstract Entity convertViewToEntity();

	public abstract List<Entity> convertViewsToEntities(List<View> viewList);
}
