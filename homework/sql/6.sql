Select distinct Product.maker, Laptop.speed
from Laptop inner join Product
on Laptop.model = Product.model and Laptop.hd >= 10;