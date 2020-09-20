Select distinct P.model, C.model, P.speed, P.ram
from PC P, PC C
where P.speed = C.speed and P.ram = C.ram and P.model > C.model