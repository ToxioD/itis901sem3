SELECT COUNT(maker) AS qty
FROM (
SELECT maker
FROM Product
GROUP BY maker
HAVING COUNT(model)=1
) AS t1;