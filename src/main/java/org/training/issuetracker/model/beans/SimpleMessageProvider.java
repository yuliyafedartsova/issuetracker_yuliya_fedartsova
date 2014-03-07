package org.training.issuetracker.model.beans;

public class SimpleMessageProvider implements MessageProvider {

	MessageResolver resolver;
	
	public String getTitle() {
		return resolver.getTitle();
	}

	public String getHelloMessage() {
		return resolver.getHelloMessage();
	}

	public void setResolver(MessageResolver resolver) {
		this.resolver = resolver;
	} 
}
