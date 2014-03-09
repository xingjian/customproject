package com.zysupermap.view
{
	import com.supermap.web.components.Compass;
	import com.supermap.web.core.Rectangle2D;
	
	import flash.events.MouseEvent;
	
	public class CompassCustom extends Compass
	{
		[Binable]
		public var defaultFullExtent:Rectangle2D;
		
		public function CompassCustom()
		{
			super();
		}
		
		override protected function showEntire(event:MouseEvent):void{
			this.map.viewBounds = defaultFullExtent;
		}
	}
}