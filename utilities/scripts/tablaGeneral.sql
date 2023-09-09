-- Crear una nueva tabla llamada TablaGeneral
CREATE TABLE IF NOT EXISTS TablaGeneral (
    Usuario VARCHAR(30),
    Coordenada VARCHAR(30),
    Tipo VARCHAR(30),
    Arsenal VARCHAR(30),
    Dia VARCHAR(30)
);
SELECT 
    C.usuarioId AS Usuario,
    C.Geoposicion AS Coordenada,
    CT.Tipo AS Tipo,
    A.ArsenalTipo AS Arsenal,
    H.Dia AS Dia
FROM DR_COORDENADA C
LEFT JOIN DR_COORDENADATIPO CT ON C.usuarioId = CT.Id
LEFT JOIN DR_ARSENAL A ON CT.Id = A.Id
LEFT JOIN DR_HORARIOS H ON A.Id = H.Id;