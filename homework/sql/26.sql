SELECT sum(s.price)/sum(s.count) as average FROM
(SELECT price,1 as count
FROM PC, Product
WHERE PC.model=Product.model AND Product.maker='A'
UNION all
SELECT price,1 as count
FROM laptop,product
WHERE laptop.model=product.model AND product.maker='A') as s;