package com.zysupermap.event
{
	import flash.events.Event;

	public class AppEvent extends Event
	{
		public var app_error:String = "appError";
		public var app_mapconfig:String = "appMapConfig";
		public var app_showposition:String = "appShowPosition";
		public var app_aroundanalyse:String="apparoundanalyse";
		public var app_getmisdata:String = "getMisData";
		public var app_loadmapconfigsuccess:String = "loadMapconfigsuccess";
		
		//构造函数
		public function AppEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false, data:Object=null)
		{
			if (data != null) _data = data;
			super(type, bubbles, cancelable);
		}
		
		//事件携带数据		
		private var _data:Object;
		
		public function get data():Object
		{
			return _data;
		} 
		
		public function set data(data:Object):void
		{
			_data = data;
		}
	}
}