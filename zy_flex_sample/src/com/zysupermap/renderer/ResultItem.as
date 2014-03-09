package com.zysupermap.renderer
{
	import com.supermap.web.core.Feature;
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
            }
        }

        return point;
    }
}

}
