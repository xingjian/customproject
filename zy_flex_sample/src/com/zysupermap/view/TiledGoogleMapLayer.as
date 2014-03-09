package com.zysupermap.view
{
	import com.supermap.web.core.*;
	import com.supermap.web.mapping.TiledCachedLayer;
	import flash.display.*;
	import flash.events.*;
	import flash.net.*;
	import mx.events.*;
	
	public class TiledGoogleMapLayer extends TiledCachedLayer
	{
		public  var satelliteURL:String = "http://mt{subdomain}.google.cn/vt/lyrs=s@140&hl=zh-CN&gl=cn&&{tileurl}&s={GALILEO}";
		public  var terrainURL:String = "http://mt{subdomain}.google.cn/vt/lyrs=t@130,r@203000000&hl=zh-CN&gl=cn&{tileurl}&s={GALILEO}";
		public  var annotationURL:String = "http://mt{subdomain}.google.cn/vt/imgtp=png32&lyrs=h@130&hl=zh-CN&gl=cn&{tileurl}&s={GALILEO}";
		public  var trafficURL:String = "http://mt{subdomain}.google.cn/vt/lyrs=m@202000000&hl=zh-CN&gl=cn&{tileurl}&s={GALILEO}";
		private var _mapType:String;
		private var _mapTypeChanged:Boolean;
		public  const MAP_TYPE_MAP:String = "map";
		public  const MAP_TYPE_SATELLITE:String = "satellite";
		public  const MAP_TYPE_TERRAIN:String = "terrain";
		public  const MAP_TYPE_ANNOTATION:String = "annotation";
		
		public function TiledGoogleMapLayer(){
			this.bounds = new Rectangle2D(-20037508.3392, -20037508.3392, 20037508.3392, 20037508.3392);
			this.CRS = new CoordinateReferenceSystem(900913);
			this.buildTileInfo();
			setLoaded(true);
		}
		
		public function get mapType():String{
			return _mapType;
		}
		
		public function set mapType(value:String):void{
			var old_mapType:String = this.mapType;
			if (old_mapType !== value){
				if (this._mapType != value){
					this._mapType = value;
					this._mapTypeChanged = true;
					invalidateProperties();
				}
				if (this.hasEventListener("propertyChange")) {
					this.dispatchEvent(PropertyChangeEvent.createUpdateEvent(this, "mapType", old_mapType, value));
				}
			}
		}
		
		override protected function getTileURL(row:int, col:int, level:int) : URLRequest{
			var serverURL:String;
			var serverID:int;
			switch(this._mapType)  {
				case MAP_TYPE_MAP:
					serverURL = trafficURL;
					serverID = Math.round(Math.random() * 3);
					break;
				case MAP_TYPE_SATELLITE:
					serverURL = satelliteURL;
					serverID = Math.round(Math.random() * 3);
					break;
				case MAP_TYPE_TERRAIN:
					serverURL = terrainURL;
					serverID = Math.round(Math.random() * 3);
					break;
				case MAP_TYPE_ANNOTATION:
					serverURL = annotationURL;
					serverID = Math.round(Math.random() * 3);
					break;
				default:
					serverURL = trafficURL;
					break;
			}
			var tileUrl:String = "x=" + col + "&y=" + row + "&z=" + level;
			serverURL = serverURL.replace("{subdomain}", serverID);
			serverURL = serverURL.replace("{tileurl}", tileUrl);
			serverURL = serverURL.replace("{GALILEO}", "Galileo".substring(0,((3 * col + row) & 7)));
			var urlRequest:URLRequest = null;
			urlRequest =  new URLRequest(serverURL);
			return urlRequest;
		}
		
		override protected function commitProperties() : void{
			super.commitProperties();
			if (this._mapTypeChanged){
				removeAllChildren();
				this._mapTypeChanged = false;
				invalidateLayer();
			}
		}
		
		private function buildTileInfo() : void{
			this.tileSize = 256;
			this.origin = new Point2D(-20037508.3392, 20037508.3392);
			this.resolutions = [
				156543.0339, 78271.51695, 39135.758475, 19567.8792375,
				9783.939619, 4891.969809, 2445.984905, 1222.992452, 
				611.496226, 305.748113, 152.874057, 76.437028,
				38.218514, 19.109257, 9.554629, 4.777314,
				2.388657, 1.194328, 0.597164, 0.298582,
				0.149291, 0.074646, 0.037323];
		}
	}
}