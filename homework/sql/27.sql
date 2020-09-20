SELECT Product.maker, AVG(PC.hd)
FROM PC, Product WHERE Product.model = PC.model
AND Product.maker IN (
SELECT DISTINCT maker
FROM Product
WHERE Product.type = 'printer')
GROUP BY maker;