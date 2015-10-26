var map,markers,marker;
var gml,cabecerasMunicipales,urlpath,urlpathCabeceras;
var arrMarkers = [];
window.onload = function () {
///////////
    var loc = window.location.href;
    var fileNamePart = loc.split('/');
    urlpath=fileNamePart[0]+'/'+fileNamePart[1]+'/'+fileNamePart[2]+'/'+fileNamePart[3]+'/'+'resources/js/json/narinoAdmin.json';
    urlpathIdeamStations=fileNamePart[0]+'/'+fileNamePart[1]+'/'+fileNamePart[2]+'/'+fileNamePart[3]+'/'+'resources/js/json/IDEAMstations.json';
    markers = new OpenLayers.Layer.Markers("Punto");
    OpenLayers.Control.Click = OpenLayers.Class(OpenLayers.Control, {
        defaultHandlerOptions: {
            'single': true,
            'double': false,
            'pixelTolerance': 0,
            'stopSingle': false,
            'stopDouble': false
        },
        initialize: function (options) {
            //this.handlerOptions = OpenLayers.Util.extend({}, this.defaultHandlerOptions);
            //OpenLayers.Control.prototype.initialize.apply(this, arguments);
            this.handler = new OpenLayers.Handler.Click(
                    this, {
                        'click': this.trigger
                    }, this.handlerOptions
                    );
        },
        trigger: function (e) {
            var lonlat = map.getLonLatFromPixel(e.xy);
            document.getElementById('frmlatlon:latitudeCap').value = Math.round(lonlat.lat);
            document.getElementById('frmlatlon:longitudeCap').value = Math.round(lonlat.lon);
            ////////REPOYECCION
            var firstProjection = 'EPSG:3857';
            var secondProjection = 'EPSG:4326';
            var result=proj4(firstProjection,secondProjection,[lonlat.lon,lonlat.lat]);
            document.getElementById('frmlatlon:lon4326').value = result[0].toFixed(5);
            document.getElementById('frmlatlon:lat4326').value = result[1].toFixed(5);
            //////////Evento disparado al simular click sobre el el mapa
            var fireOnThis = document.getElementById("frmlatlon:btAjax");
            var evObj = document.createEvent('Event');
            evObj.initEvent('click', true, true);
            fireOnThis.dispatchEvent(evObj);
            //point
            map.addLayer(markers);
            marker = new OpenLayers.Marker(lonlat);
            markers.addMarker(marker);

        }

    });
////////////    
    var bounds = new OpenLayers.Bounds(
            -80.80241888771431, 0.5124091384021122,
            -78.63857187459485, 2.3940265905821643
            );
    var options = {
        maxExtent: bounds,
        maxResolution: 0.0084525273949979,
        projection: "EPSG:4326",
        units: 'degrees'
    };
  
    
    map = new OpenLayers.Map('map', options);
    map.addControl(new OpenLayers.Control.LayerSwitcher());
    
    var gmap = new OpenLayers.Layer.Google(
        "Google Streets", // the default
        {numZoomLevels: 20}
    );

    var ghyb = new OpenLayers.Layer.Google(
            "Google Hybrid",
            {type: google.maps.MapTypeId.HYBRID, numZoomLevels: 20}
    );
    gml = new OpenLayers.Layer.Vector("NarinoAdministrativo", {
        projection: new OpenLayers.Projection("EPSG:3857"),
        strategies: [new OpenLayers.Strategy.Fixed()],
        protocol: new OpenLayers.Protocol.HTTP({
            url: urlpath,
            format: new OpenLayers.Format.GeoJSON()
        }),
        styleMap: new OpenLayers.StyleMap({
            "default": new OpenLayers.Style({
                pointRadius: 5,
                fillOpacity: 0,
                strokeColor: "#000000",
                strokeWidth: 1,
                strokeOpacity: 1}) //Text entspricht feature.attributes.name
        })

    });
    //CABECERAS MUNICIPALES
    estacionesIDEAM = new OpenLayers.Layer.Vector("Estaciones IDEAM", {
        projection: new OpenLayers.Projection("EPSG:3857"),
        strategies: [new OpenLayers.Strategy.Fixed()],
        protocol: new OpenLayers.Protocol.HTTP({
            url: urlpathIdeamStations,
            format: new OpenLayers.Format.GeoJSON()
        }),
        styleMap: new OpenLayers.StyleMap({
            "default": new OpenLayers.Style({
                pointRadius: 3,
                fillOpacity: 2,
                strokeColor: "#FF4000",
                strokeWidth: 3,
                strokeOpacity: 0.5}) //Text entspricht feature.attributes.name
        })

    });
    // Google.v3 uses EPSG:900913 as projection, so we have to // transform our coordinates
    map.addLayers([ghyb,gmap,gml,estacionesIDEAM,markers]);// 
    map.setCenter(new OpenLayers.LonLat(-77.868, 1.409).transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
            ), 8);

    var click = new OpenLayers.Control.Click();
    map.addControl(click);
    click.activate();

    
}

//FUNCION PARA CARGAR LAS CAPAS SELECCIONADAS EN LA APLICACION

function reproject3857() {
    var lat=document.getElementById('frmlatlon:lat4326').value;
    var lon=document.getElementById('frmlatlon:lon4326').value;
    ////////REPOYECCION
    var firstProjection = 'EPSG:4326';
    var secondProjection = 'EPSG:3857';
    var result = proj4(firstProjection, secondProjection, [lon,lat]);
    document.getElementById('frmlatlon:latitudeCap').value = Math.round(result[1],1);
    document.getElementById('frmlatlon:longitudeCap').value = Math.round(result[0],1);
}