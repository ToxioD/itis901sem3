SELECT DISTINCT maker
FROM Product pr JOIN PC p ON pr.model=p.model
WHERE speed>=750 AND maker IN (
SELECT maker
FROM Product pr JOIN Laptop l ON pr.model=l.model
WHERE speed>=750);