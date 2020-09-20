SELECT Product.maker, MAX(PC.price)
FROM Product, PC WHERE Product.model = PC.model
GROUP BY Product.maker;