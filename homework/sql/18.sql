SELECT DISTINCT Product.maker, Printer.price
FROM Product, Printer
WHERE Product.model = Printer.model
AND Printer.color = 'y'
AND Printer.price = (
SELECT MIN(price) FROM printer
WHERE printer.color = 'y'
);