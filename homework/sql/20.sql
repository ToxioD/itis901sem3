SELECT maker, COUNT(model)
FROM Product WHERE type = 'pc'
GROUP BY Product.maker
HAVING COUNT (DISTINCT model) >= 3;