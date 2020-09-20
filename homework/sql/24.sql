SELECT model
FROM (
SELECT model, price
FROM PC
UNION
SELECT model, price
FROM Laptop
UNION
SELECT model, price
FROM Printer
) q1
WHERE price = (
SELECT MAX(price)
FROM (
SELECT price
FROM PC
UNION
SELECT price
FROM Laptop
UNION
SELECT price
FROM Printer
) q2
);