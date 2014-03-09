package com.zysupermap.view
{
	
	import com.zysupermap.renderer.QueryResultItemRenderer;
	
	import mx.core.ClassFactory;
	
	import spark.components.DataGroup;
	
	[Event(name="queryResultClick", type="flash.events.Event")]
	[Event(name="queryResultMouseOver", type="flash.events.Event")]
	[Event(name="queryResultMouseOut", type="flash.events.Event")]
	[Event(name="showMessage", type="com.zysupermap.event.AppEvent")]
	[Event(name="showPosition", type="com.zysupermap.event.AppEvent")]
	
	public class QueryResultDataGroup extends DataGroup
	{
		public function QueryResultDataGroup()
		{
			super();
			this.itemRenderer = new ClassFactory(QueryResultItemRenderer);
		}
	}
}