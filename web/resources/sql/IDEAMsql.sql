--LISTAR TIPO VARIABLES DE UNA ESTACION
SELECT 	
    variable_id, name_variable 
FROM	
    stations
NATURAL JOIN
    samples
NATURAL JOIN
    variables
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
--LISTAR AÑOS DE UNA ESTACION
SELECT
    distinct year
FROM
    stations
NATURAL JOIN
    samples
NATURAL JOIN
    variables
NATURAL JOIN
    months
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
    AND variable_id=1
    order by year
--LISTAR MESES DEL AÑO PARTICULAR EN UNA ESTACION

SELECT
    month
FROM
    stations
NATURAL JOIN
    samples
NATURAL JOIN
    variables
NATURAL JOIN
    months
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
    AND variable_id=1
    and year=1965 order by month

--valor de captura estacion unidades
SELECT 
	value_point, units
FROM
	stations
NATURAL JOIN
	samples
NATURAL JOIN
	variables
NATURAL JOIN
	months
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
    AND variable_id=1
    AND year=1965
    AND month=2


--PROMEDIO ANUAL
SELECT 
	value_point, units
FROM
	stations
NATURAL JOIN
	samples
NATURAL JOIN
	variables
NATURAL JOIN
	years
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
    AND variable_id=1
    AND year=1965

--GRAFICOS IEAM
--AGREGADOS AÑOS ESTACION
SELECT
    year,variable_id,value_point,units
FROM
    stations
NATURAL JOIN
    samples
NATURAL JOIN
    variables
NATURAL JOIN
    years
WHERE
    latitude_3857=-8572050
    AND longitude_3857=155700
    AND variable_id=1
    group by 1,2,3,4
    order by 1,2

--MESES
SELECT 
    station_id, value_point, units
FROM	
    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN years
WHERE
    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=1

---CONSULTA  PROMEDIO MENSUAL
SELECT m.* from(
	(SELECT 
	    station_id, name_variable,units,round(cast(avg(value_point) AS numeric) , 2) as enero
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=1
	GROUP BY 1,2,3) as m1
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as febrero
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=2
	GROUP BY 1,2) as m2
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as marzo
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=3
	GROUP BY 1,2) as m3
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as abril
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=4
	GROUP BY 1,2) as m4
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as mayo
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=5
	GROUP BY 1,2) as m5
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as junio
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=6
	GROUP BY 1,2) as m6
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as julio
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=7
	GROUP BY 1,2) as m7
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as agosto
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=8
	GROUP BY 1,2) as m8
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as septiembre
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=9
	GROUP BY 1,2) as m9
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as octubre
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=10
	GROUP BY 1,2) as m10
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as noviembre
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=11
	GROUP BY 1,2) as m11
	NATURAL JOIN
	(SELECT 
	    station_id, name_variable,round(cast(avg(value_point) AS numeric) , 2) as diciembre
	FROM	
	    stations NATURAL JOIN samples NATURAL JOIN variables NATURAL JOIN months
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND month=12
	GROUP BY 1,2) as m13
)m

--SELECT * from stations
