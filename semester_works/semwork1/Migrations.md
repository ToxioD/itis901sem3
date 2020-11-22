# Tables
### Users
```sql
create table users(
    id bigserial primary key,
    email varchar(30),
    hashPassword varchar,
    firstName varchar(20),
    lastName varchar(20)
);
```
### Rolls
```sql
create table rolls (
	id bigserial primary key,
	userId bigint references users,
	dices varchar(20),
	result int,
	color varchar(20)
);
```
### Trinkets
```sql
create table trinkets (
	id bigserial primary key,
	name varchar(200)
);
```
### Photos
```sql
create table photos (
	id bigserial primary key,
	path varchar
);
```
###Classes
```sql
create table classes(
	id bigserial primary key,
	name varchar(15),
	description text,
	hitDice integer,
	isSpellcasting boolean
);
```
###Races
```sql
create table races(
	id bigserial primary key,
	name varchar(15),
	description text,
	ability varchar,
	size varchar(15),
	speed integer,
	hasDarkvision boolean
);
```
# Values
### Trinkets
```sql
insert into trinkets(name) values ('A mummified goblin hand'),
('A piece of crystal that faintly glows in the moonlight'),
('A gold coin minted in an unknown land'),
('A diary written in a language you don’t know'),
('A brass ring that never tarnishes'),
('An old chess piece made from glass'),
('A pair of knucklebone dice, each with a skull symbol on the side that would normally show six pips'),
('A small idol depicting a nightmarish creature that gives you unsettling dreams when you sleep near it'),
('A rope necklace from which dangles four mummified elf fingers'),
('The deed for a parcel of land in a realm unknown to you'),
('A 1-ounce block made from an unknown material'),
('A small cloth doll skewered with needles'),
('A tooth from an unknown beast'),
('An enormous scale, perhaps from a dragon'),
('A bright green feather'),
('An old divination card bearing your likeness'),
('A glass orb filled with moving smoke'),
('A 1-pound egg with a bright red shell'),
('A pipe that blows bubbles'),
('A glass jar containing a weird bit of flesh floating in pickling fluid'),
('A tiny gnome-crafted music box that plays a song you dimly remember from your childhood'),
('A small wooden statuette of a smug halfling'),
('A brass orb etched with strange runes'),
('A multicolored stone disk'),
('A tiny silver icon of a raven'),
('A bag containing forty-seven humanoid teeth, one of which is rotten'),
('A shard of obsidian that always feels warm to the touch'),
('A dragon''s bony talon hanging from a plain leather necklace'),
('A pair of old socks'),
('A blank book whose pages refuse to hold ink, chalk, graphite, or any other substance or marking'),
('A silver badge in the shape of a five-pointed star'),
('A knife that belonged to a relative'),
('A glass vial filled with nail clippings'),
('A rectangular metal device with two tiny metal cups on one end that throws sparks when wet'),
('A white, sequined glove sized for a human'),
('A vest with one hundred tiny pockets'),
('A small, weightless stone block'),
('A tiny sketch portrait of a goblin'),
('An empty glass vial that smells of perfume when opened'),
('A gemstone that looks like a lump of coal when examined by anyone but you'),
('A scrap of cloth from an old banner'),
('A rank insignia from a lost legionnaire'),
('A tiny silver bell without a clapper'),
('A mechanical canary inside a gnome-crafted lamp'),
('A tiny chest carved to look like it has numerous feet on the bottom'),
('A dead sprite inside a clear glass bottle'),
('A metal can that has no opening but sounds as if it is filled with liquid, sand, spiders, or broken glass (your choice)'),
('A glass orb filled with water, in which swims a clockwork goldfish'),
('A silver spoon with an M engraved on the handle'),
('A whistle made from gold-colored wood'),
('A dead scarab beetle the size of your hand'),
('Two toy soldiers, one with a missing head'),
('A small box filled with different-sized buttons'),
('A candle that can’t be lit'),
('A tiny cage with no door'),
('An old key'),
('An indecipherable treasure map'),
('A hilt from a broken sword'),
('A rabbit’s foot'),
('A glass eye'),
('A cameo carved in the likeness of a hideous person'),
('A silver skull the size of a coin'),
('An alabaster mask'),
('A pyramid of sticky black incense that smells very bad'),
('A nightcap that, when worn, gives you pleasant dreams'),
('A single caltrop made from bone'),
('A gold monocle frame without the lens'),
('A 1-inch cube, each side painted a different color'),
('A crystal knob from a door'),
('A small packet filled with pink dust'),
('A fragment of a beautiful song, written as musical notes on two pieces of parchment'),
('A silver teardrop earring made from a real teardrop'),
('The shell of an egg painted with scenes of human misery in disturbing detail'),
('A fan that, when unfolded, shows a sleeping cat'),
('A set of bone pipes'),
('A four-leaf clover pressed inside a book discussing manners and etiquette'),
('A sheet of parchment upon which is drawn a complex mechanical contraption'),
('An ornate scabbard that fits no blade you have found so far'),
('An invitation to a party where a murder happened'),
('A bronze pentacle with an etching of a rat''s head in its center'),
('A purple handkerchief embroidered with the name of a powerful archmage'),
('Half of a floorplan for a temple, castle, or some other structure'),
('A bit of folded cloth that, when unfolded, turns into a stylish cap'),
('A receipt of deposit at a bank in a far-flung city'),
('A diary with seven missing pages'),
('An empty silver snuffbox bearing an inscription on the surface that says "dreams"'),
('An iron holy symbol devoted to an unknown god'),
('A book that tells the story of a legendary hero''s rise and fall, with the last chapter missing'),
('A vial of dragon blood'),
('An ancient arrow of elven design'),
('A needle that never bends'),
('An ornate brooch of dwarven design'),
('An empty wine bottle bearing a pretty label that says, "The Wizard of Wines Winery, Red Dragon Crush, 331422-W"'),
('A mosaic tile with a multicolored, glazed surface'),
('A petrified mouse'),
('A black pirate flag adorned with a dragon''s skull and crossbones'),
('A tiny mechanical crab or spider that moves about when it’s not being observed'),
('A glass jar containing lard with a label that reads, "Griffon Grease"'),
('A wooden box with a ceramic bottom that holds a living worm with a head on each end of its body'),
('A metal urn containing the ashes of a hero');
```
###Classes
```sql
insert into classes(name, description, hitDice, isSpellcasting) values ('Bard', 'In the worlds of D&D, words and music are not just vibrations of air, but vocalizations with power all their own. The bard is a master of song, speech, and the magic they contain. Bards say that the multiverse was spoken into existence, that the words of the gods gave it shape, and that echoes of these primordial Words of Creation still resound throughout the cosmos. The music of bards is an attempt to snatch and harness those echoes, subtly woven into their spells and powers.', 8, true),
('Cleric', 'Divine magic, as the name suggests, is the power of the gods, flowing from them into the world. Clerics are conduits for that power, manifesting it as miraculous effects. The gods don’t grant this power to everyone who seeks it, but only to those chosen to fulfill a high calling.', 8, true),
('Druid', 'Druids revere nature above all, gaining their spells and other magical powers either from the force of nature itself or from a nature deity. Many druids pursue a mystic spirituality of transcendent union with nature rather than devotion to a divine entity, while others serve gods of wild nature, animals, or elemental forces. The ancient druidic traditions are sometimes called the Old Faith, in contrast to the worship of gods in temples and shrines.', 8, true),
('Fighter', 'Fighters learn the basics of all combat styles. Every fighter can swing an axe, fence with a rapier, wield a longsword or a greatsword, use a bow, and even trap foes in a net with some degree of skill. Likewise, a fighter is adept with shields and every form of armor. Beyond that basic degree of familiarity, each fighter specializes in a certain style of combat. Some concentrate on archery, some on fighting with two weapons at once, and some on augmenting their martial skills with magic.', 10, false),
('Monk', 'Monks make careful study of a magical energy that most monastic traditions call ki. This energy is an element of the magic that suffuses the multiverse—specifically, the element that flows through living bodies. Monks harness this power within themselves to create magical effects and exceed their bodies’ physical capabilities, and some of their special attacks can hinder the flow of ki in their opponents. Using this energy, monks channel uncanny speed and strength into their unarmed strikes. As they gain experience, their martial training and their mastery of ki gives them more power over their bodies and the bodies of their foes.', 8, false),
('Paladin', 'A paladin swears to uphold justice and righteousness, to stand with the good things of the world against the encroaching darkness, and to hunt the forces of evil wherever they lurk. Different paladins focus on various aspects of the cause of righteousness, but all are bound by the oaths that grant them power to do their sacred work. Although many paladins are devoted to gods of good, a paladin’s power comes as much from a commitment to justice itself as it does from a god.', 10, true),
('Ranger', 'Warriors of the wilderness, rangers specialize in hunting the monsters that threaten the edges of civilization—humanoid raiders, rampaging beasts and monstrosities, terrible giants, and deadly dragons. They learn to track their quarry as a predator does, moving stealthily through the wilds and hiding themselves in brush and rubble. Rangers focus their combat training on techniques that are particularly useful against their specific favored foes.', 10, false),
('Rogue', 'Rogues devote as much effort to mastering the use of a variety of skills as they do to perfecting their combat abilities, giving them a broad expertise that few other characters can match. Many rogues focus on stealth and deception, while others refine the skills that help them in a dungeon environment, such as climbing, finding and disarming traps, and opening locks.', 8, false),
('Sorcerer', 'Magic is a part of every sorcerer, suffusing body, mind, and spirit with a latent power that waits to be tapped. Some sorcerers wield magic that springs from an ancient bloodline infused with the magic of dragons. Others carry a raw, uncontrolled magic within them, a chaotic storm that manifests in unexpected ways.', 6, true),
('Warlock', 'A warlock is defined by a pact with an otherworldly being. Sometimes the relationship between warlock and patron is like that of a cleric and a deity, though the beings that serve as patrons for warlocks are not gods. A warlock might lead a cult dedicated to a demon prince, an archdevil, or an utterly alien entity—beings not typically served by clerics. More often, though, the arrangement is similar to that between a master and an apprentice. The warlock learns and grows in power, at the cost of occasional services performed on the patron’s behalf.', 8, true),
('Wizard', 'Wild and enigmatic, varied in form and function, the power of magic draws students who seek to master its mysteries. Some aspire to become like the gods, shaping reality itself. Though the casting of a typical spell requires merely the utterance of a few strange words, fleeting gestures, and sometimes a pinch or clump of exotic materials, these surface components barely hint at the expertise attained after years of apprenticeship and countless hours of study.', 6, true);
```
###Races
```sql
insert into races(name, description, ability, size, speed, hasDarkvision) values ('Dragonborn', 'Born of dragons, as their name proclaims, the dragonborn walk proudly through a world that greets them with fearful incomprehension. Shaped by draconic gods or the dragons themselves, dragonborn originally hatched from dragon eggs as a unique race, combining the best attributes of dragons and humanoids. Some dragonborn are faithful servants to true dragons, others form the ranks of soldiers in great wars, and still others find themselves adrift, with no clear calling in life.', 'Your Strength score increases by 2, and your Charisma score increases by 1.', 'Medium', 30, false),
('Dwarf', 'Kingdoms rich in ancient grandeur, halls carved into the roots of mountains, the echoing of picks and hammers in deep mines and blazing forges, a commitment to clan and tradition, and a burning hatred of goblins and orcs—these common threads unite all dwarves.', 'Your Constitution score increases by 2.', 'Medium', 25, true),
('Elf', 'Elves are a magical people of otherworldly grace, living in the world but not entirely part of it. They live in places of ethereal beauty, in the midst of ancient forests or in silvery spires glittering with faerie light, where soft music drifts through the air and gentle fragrances waft on the breeze. Elves love nature and magic, art and artistry, music and poetry, and the good things of the world.', 'Your Dexterity score increases by 2.', 'Medium', 30, true),
('Gnome', 'A constant hum of busy activity pervades the warrens and neighborhoods where gnomes form their close-knit communities. Louder sounds punctuate the hum: a crunch of grinding gears here, a minor explosion there, a yelp of surprise or triumph, and especially bursts of laughter. Gnomes take delight in life, enjoying every moment of invention, exploration, investigation, creation, and play.', 'Your Intelligence score increases by 2.', 'Small', 25, true),
('Half-Elf', 'Walking in two worlds but truly belonging to neither, half-elves combine what some say are the best qualities of their elf and human parents: human curiosity, inventiveness, and ambition tempered by the refined senses, love of nature, and artistic tastes of the elves. Some half-elves live among humans, set apart by their emotional and physical differences, watching friends and loved ones age while time barely touches them. Others live with the elves, growing restless as they reach adulthood in the timeless elven realms, while their peers continue to live as children. Many half-elves, unable to fit into either society, choose lives of solitary wandering or join with other misfits and outcasts in the adventuring life.', 'Your Charisma score increases by 2, and two other ability scores of your choice increase by 1.', 'Medium', 30, true),
('Halfling', 'The comforts of home are the goals of most halflings’ lives: a place to settle in peace and quiet, far from marauding monsters and clashing armies; a blazing fire and a generous meal; fine drink and fine conversation. Though some halflings live out their days in remote agricultural communities, others form nomadic bands that travel constantly, lured by the open road and the wide horizon to discover the wonders of new lands and peoples. But even these wanderers love peace, food, hearth, and home, though home might be a wagon jostling along a dirt road or a raft floating downriver.', 'Your Dexterity score increases by 2.', 'Small', 25, false),
('Half-Orc', 'Whether united under the leadership of a mighty warlock or having fought to a standstill after years of conflict, orc and human tribes sometimes form alliances, joining forces into a larger horde to the terror of civilized lands nearby. When these alliances are sealed by marriages, half-orcs are born. Some half-orcs rise to become proud chiefs of orc tribes, their human blood giving them an edge over their full-blooded orc rivals. Some venture into the world to prove their worth among humans and other more civilized races. Many of these become adventurers, achieving greatness for their mighty deeds and notoriety for their barbaric customs and savage fury.', 'Your Strength score increases by 2, and your Constitution score increases by 1.', 'Medium', 30, true),
('Human', 'In the reckonings of most worlds, humans are the youngest of the common races, late to arrive on the world scene and short-lived in comparison to dwarves, elves, and dragons. Perhaps it is because of their shorter lives that they strive to achieve as much as they can in the years they are given. Or maybe they feel they have something to prove to the elder races, and that’s why they build their mighty empires on the foundation of conquest and trade. Whatever drives them, humans are the innovators, the achievers, and the pioneers of the worlds.', 'Your ability scores each increase by 1.', 'Medium', 30, false),
('Tiefling', 'To be greeted with stares and whispers, to suffer violence and insult on the street, to see mistrust and fear in every eye: this is the lot of the tiefling. And to twist the knife, tieflings know that this is because a pact struck generations ago infused the essence of Asmodeus—overlord of the Nine Hells—into their bloodline. Their appearance and their nature are not their fault but the result of an ancient sin, for which they and their children and their children’s children will always be held accountable.', 'Your Intelligence score increases by 1, and your Charisma score increases by 2.', 'Medium', 30, true);
```