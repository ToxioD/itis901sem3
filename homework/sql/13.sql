Select avg(PC.speed)
from PC inner join Product
on PC.model = Product.model and Product.maker = 'A';