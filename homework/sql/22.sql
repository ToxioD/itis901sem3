SELECT PC.speed, AVG(PC.price)
FROM PC WHERE PC.speed>600
GROUP BY PC.speed;