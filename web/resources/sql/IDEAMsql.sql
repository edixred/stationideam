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

///////////GRAFICOS IEAM
SELECT m.* FROM(
	(SELECT station_id, value_point, units
	FROM	stations NATURAL JOIN samples NATURAL JOIN variables
	NATURAL JOIN years
	WHERE
	    latitude_3857=-8572050 AND longitude_3857=155700 AND variable_id=1 AND year=1965) a0
	JOIN
	(SELECT station_id,value_point, units
	FROM	stations NATURAL JOIN samples NATURAL JOIN variables
	NATURAL JOIN years
	WHERE
	    variable_id=1 AND year=1966) a1

	using
	(station_id)
	JOIN
	(SELECT station_id,value_point, units
	FROM	stations NATURAL JOIN samples NATURAL JOIN variables
	NATURAL JOIN years
	WHERE
	    variable_id=1 AND year=1967) a2

	using
	(station_id)
	JOIN
	(SELECT station_id,value_point, units
	FROM	stations NATURAL JOIN samples NATURAL JOIN variables
	NATURAL JOIN years
	WHERE
	    variable_id=1 AND year=1968) a3
	using
	(station_id)
	JOIN
	(SELECT station_id,value_point, units
	FROM	stations NATURAL JOIN samples NATURAL JOIN variables
	NATURAL JOIN years
	WHERE
	    variable_id=1 AND year=1969) a4

	using
	(station_id)
)m