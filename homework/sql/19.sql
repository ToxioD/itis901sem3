SELECT Product.maker, AVG(screen)
FROM Laptop
LEFT JOIN Product ON Product.model = Laptop.model
GROUP BY Product.maker;