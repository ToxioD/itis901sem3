Select Ships.class, Ships.name, Classes.country
from Ships inner join Classes
on Classes.class = Ships.class and Classes.numGuns >= 10;