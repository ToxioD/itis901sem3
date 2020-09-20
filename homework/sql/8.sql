Select distinct maker
from Product
where type = 'PC'
except
Select distinct maker
from Product
where type = 'Laptop';