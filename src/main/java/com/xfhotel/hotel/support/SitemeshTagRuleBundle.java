package com.xfhotel.hotel.support;

import org.sitemesh.SiteMeshContext;
import org.sitemesh.content.ContentProperty;
import org.sitemesh.content.tagrules.TagRuleBundle;
import org.sitemesh.content.tagrules.html.ExportTagToContentRule;
import org.sitemesh.tagprocessor.State;

public class SitemeshTagRuleBundle implements TagRuleBundle {

	@Override
	public void cleanUp(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {

	}

	@Override
	public void install(State defaultState, ContentProperty contentProperty, SiteMeshContext siteMeshContext) {
		defaultState.addRule("e_right",
				new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("e_right"), false));
		defaultState.addRule("my_header",
				new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("my_header"), false));
		defaultState.addRule("my_script",
				new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("my_script"), false));
		defaultState.addRule("my_body",
				new ExportTagToContentRule(siteMeshContext, contentProperty.getChild("my_body"), false));
	}

}
