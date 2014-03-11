package com.zysupermap.renderer
{
	import com.supermap.web.core.Feature;
	import com.supermap.web.core.Point2D;
	import com.supermap.web.core.Rectangle2D;
	import com.supermap.web.core.geometry.GeoLine;
	import com.supermap.web.core.geometry.GeoPoint;
	import com.supermap.web.core.geometry.GeoRegion;
	import com.supermap.web.core.geometry.Geometry;
	import com.supermap.web.core.styles.Style;

public class ResultItem
{

    public function ResultItem(graphic:Feature, attributes:Object=null)
    {
        _graphic = graphic;
        _attributes = attributes;
        _center = getGeomCenter(graphic);
    }


    private var _graphic:Feature;

    public function get graphic():Feature
    {
        return _graphic;
    }

    //--------------------------------------------------------------------------
    //  attributes
    //--------------------------------------------------------------------------

    private var _attributes:Object;

    public function get attributes():Object
    {
        return _attributes;
    }

    //--------------------------------------------------------------------------
    //  center
    //--------------------------------------------------------------------------

    private var _center:GeoPoint;

    public function get center():GeoPoint
    {
        return _center;
    }

    //--------------------------------------------------------------------------
    //  geometry
    //--------------------------------------------------------------------------

    public function get geometry():Geometry
    {
        return _graphic.geometry;
    }

    //--------------------------------------------------------------------------
    //  symbol
    //--------------------------------------------------------------------------

    public function get symbol():Style
    {
        return _graphic.style;
    }

    //--------------------------------------------------------------------------
    //
    //  Methods
    //
    //--------------------------------------------------------------------------

    private function getGeomCenter(graphic:Feature):GeoPoint
    {
        var point:GeoPoint;
        var geometry:Geometry = graphic.geometry;

        if (geometry)
        {
            switch (geometry.type)
            {
                case Geometry.GEOPOINT:
                {
                    point = geometry as GeoPoint;
                    break;
                }
				case Geometry.GEOLINE:
				{
					var geoLine:GeoLine= geometry as GeoLine;
					var rect:Rectangle2D=geoLine.bounds;
					var point2d:Point2D=rect.center;
					point=new GeoPoint(point2d.x,point2d.y);
					break;
				}
				case Geometry.GEOREGION:
				{
					var geoRegion:GeoRegion=geometry as GeoRegion;
					var rect1:Rectangle2D=geoRegion.bounds;
					var point2d1:Point2D=rect1.center;
					point=new GeoPoint(point2d1.x,point2d1.y);
					break;
				}	
            }
        }

        return point;
    }
}

}
