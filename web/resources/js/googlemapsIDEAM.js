var map, markers, marker;
var gml, cabecerasMunicipales, urlpath, urlpathCabeceras;
var arrMarkers = [];
window.onload = function () {
///////////
    var loc = window.location.href;
    var fileNamePart = loc.split('/');
    urlpath = fileNamePart[0] + '/' + fileNamePart[1] + '/' + fileNamePart[2] + '/' + fileNamePart[3] + '/' + 'resources/js/json/narinoAdmin.json';
    urlpathIdeamStations = fileNamePart[0] + '/' + fileNamePart[1] + '/' + fileNamePart[2] + '/' + fileNamePart[3] + '/' + 'resources/js/json/IDEAMstations.json';

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
    
    // Google.v3 uses EPSG:900913 as projection, so we have to // transform our coordinates
    // Creamos una capa por los puntos de interés
    var poi = new OpenLayers.Layer.Vector("EstacionesIdeam", {
        // El sistema de coordenadas en el que están nuestros datos
        projection: new OpenLayers.Projection("EPSG:3857"),
        // La estrategia de carga
        strategies: [new OpenLayers.Strategy.Fixed()],
        // La página que nos entrega los datos y el formato
        protocol: new OpenLayers.Protocol.HTTP({
            url: "ideamstations.json",
            format: new OpenLayers.Format.GeoJSON()
        }),
        // Una vez se cargen los datos configurar el zoom a la extensión de los mismos
        eventListeners: {
            "featuresadded": function () {
                this.map.zoomToExtent(this.getDataExtent());
            }
        }
    });
    ///
    map.addControl(new OpenLayers.Control.MousePosition());
    // Añado un control para activar la selección de puntos
    var options = {
        // Este método se ejecuta cuando selecciono un punto
        // Se define mas abajo
        onSelect: serialize
    };
    // Registro y activo mi control
    var select = new OpenLayers.Control.SelectFeature(poi, options);
    map.addControl(select);
    select.activate();

    function serialize(feature) {
        // Configuro el cuerpo del mensaje
        var html = '';
        var firstProjection = 'EPSG:3857';
        var secondProjection = 'EPSG:4326';
        var result=proj4(firstProjection,secondProjection,[feature.geometry.x,feature.geometry.y]);
        html += '<b>EPSG:4326:</b> lat:' +result[0].toFixed(5)+" lon:"  + result[1].toFixed(5);+'</br>';
        var txtlat = document.getElementById("frmlatlon:idtxtlat");
        var txtlon = document.getElementById("frmlatlon:idtxtlon");
        txtlat.value = feature.geometry.x;
        txtlon.value = feature.geometry.y;
        
        var fireOnThis = document.getElementById("frmlatlon:btnClicK");
        var evObj = document.createEvent('Event');
        evObj.initEvent('click', true, true);
        fireOnThis.dispatchEvent(evObj);
        
        // Creo el popup
        var popup = new OpenLayers.Popup.FramedCloud(feature.data.direccion,
                feature.geometry.getBounds().getCenterLonLat(),
                null,
                html,
                null, true);
        // Se lo asigno al punto seleccionado y lo adiciono al mapa
        feature.popup = popup;
        map.addPopup(popup);
    }
            ///
            map.addLayers([ghyb, gmap, gml, poi]);// 
    map.setCenter(new OpenLayers.LonLat(-77.868, 1.409).transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
            ), 8);




}
