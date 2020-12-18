![ITIS Logo](http://salesboss.ru/itis/wp-content/uploads/2016/04/logo.itis_.png)

# Semester Work 2
> Dmitriy Lysenkov, 11-901

Second semester work for informatics on 3-rd semester.

## Installing / Getting started

Clone this repository:

```shell
git clone https://github.com/ToxioD/itis901sem3.git
```

Install IntelliJ Idea + Lombok and run

### Initial Configuration

- **Server**: Build ```ru.itis.application.MainForServer```
- **Client**: Build ```ru.itis.application.Main```

## Features

This project contains a two-player game with following features:
* Server-client architecture on sockets
* JavaFX multi-screen interface
* Chat on startup
* AutoBattler gameplay mechanics
* Buffs and debuffs buying between rounds

## Game Rules

A **player's** goal is to low **opponent's** *health points (hp)* down to/below 0.
This could be done through **autobattler** mechanics, which resolve automatically:
player can deal *damage* to it's opponent with a certain *hit chance*, which lowers it's hp.
Every damage point player take earns it a *gold* piece. Every round player also get a fixed amount of gold.
Player can buy *modifiers* spending this gold: before every round
player can choose between a *buff*, which will be applied to your *attributes (hp, hit chance, etc.)*, 
and a *debuff*, which will be applied to it's opponent's attributes.
Player can also *refresh* these modifiers spending 1 gold piece.

## Links

- [Repository](https://github.com/ToxioD/itis901sem3/tree/master/semester_works/semwork2)
- Related projects:
  - [JavaFX](https://gitlab.com/MarselSidikov/11-901/-/tree/master/Themes/06.%20JavaFx) by Sidikov Marsel
  - [Sockets](https://gitlab.com/MarselSidikov/11-901/-/tree/master/Themes/07.%20Sockets) by Sidikov Marsel
