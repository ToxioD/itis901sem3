Select distinct Product.maker
from Product inner join PC
on Product.model = PC.model and PC.speed >= 450;