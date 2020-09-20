select distinct Product.type, Laptop.model, Laptop.speed
from Laptop inner join Product
on Product.model = Laptop.model
where Laptop.speed < (
select min(speed)
from pc);