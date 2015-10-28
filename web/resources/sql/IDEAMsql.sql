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
