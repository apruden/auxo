package com.monolito.auxo.client.local;

import javax.inject.Inject;

import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.InitialState;
import com.monolito.auxo.shared.SharedList;
import com.monolito.auxo.shared.TodoItem;
import org.jboss.errai.ui.client.widget.HasModel;
import org.jboss.errai.ui.client.widget.ListWidget;
import org.jboss.errai.ui.shared.api.annotations.AutoBound;
import org.jboss.errai.ui.shared.api.annotations.Bound;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Composite;

/**
 * @author alex
 */
@Templated("TodoListPage.html#sharedList")
public class SharedListWidget extends Composite implements HasModel<SharedList> {

	@Inject
	@AutoBound
	DataBinder<SharedList> sharedListDataBinder;
	@Bound
	@DataField
	Element userName = DOM.createElement("legend");
	@Inject
	@Bound
	@DataField
	ListWidget<TodoItem, LockedTodoItemWidget> items;

	@Override
	public SharedList getModel() {
		return sharedListDataBinder.getModel();
	}

	@Override
	public void setModel(SharedList model) {
		sharedListDataBinder.setModel(model, InitialState.FROM_MODEL);
	}
}
