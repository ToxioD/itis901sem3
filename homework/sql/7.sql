Select distinct PC.model, PC.price
from PC inner join Product
on Product.maker = 'B' and Product.model = PC.model
union
Select distinct Laptop.model, Laptop.price
from Laptop inner join Product
on Product.maker = 'B' and Product.model = Laptop.model
union
Select distinct Printer.model, Printer.price
from Printer inner join Product
on Product.maker = 'B' and Product.model = Printer.model;