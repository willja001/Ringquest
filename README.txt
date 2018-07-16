RingQuest

Beta Version 2.1
5/15/2018
William Diehl

Subject matter adapted from J.R.R. Tolkien "Lord of the Rings" and other sources; game material is 
subject to copyright from author(s), and publishers, and not intended for commercial uses or resale.

Install Instructions:

1. Download Java SE Development Kit (JDK) 8 or higher from
http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2. Choose the version appropriate to your platform and operating system (e.g., Linux x86, Windows)

3. Follow installation instructions provided by Oracle.

4. Set path, classpath, or environmental variables according to Oracle instructions. An example is:
PATH=$PATH:\home\myname\jdk1.0.0_171\bin
export PATH

5. Install quest directory.

6. Navigate to quest directory. Run quest.sh (If using windows, change quest.sh to quest.bat).
Set execution permissions as required. Note: .java files can be individually compiled using javac filename.java

7. To execute, enter "java RinqQuest"

Individual Compiling Instructions:

Compile executable Java versions of the following classes in the following order in one directory:

Action.java
AnimateType.java
FiniteType.java
ImmovableType.java
MovableType.java
InteractType.java
Movement.java
Location.java
PlayerStatus.java
RingQuest.java

"RingQuest" is the top-level executable.

The file "ringquesttext.txt" must be in the same directory.

- Enter your commands using only lowercase letters, with no punctuation.  Use the imperative mood, with full sentences. Ex:
"go east", "kill the orc","take the ring", etc.

- The following commands are incorporated using syntax as indicated:

Command {go}, {walk}, {run}, {} : Direction [north, n], [northeast, ne],[east, e],[southeast, se],[south, s],	
	[southwest, sw],[west, w], [northwest, nw],[up, u],[down, d],[in, i, inside],[out, o, outside]
 
Command [Direction] : implies a "go" command in [Direction]
Command {cross} : Direct Object [ImmovableType]
Command {kill},{hit},{fight},{attack} : Direct Object [AnimateType] : Instrument {with},{using} [MovableType]
Command {shoot} : Direct Object [AnimateType]
Command {throw} : Direct Object [MovableType] : {at} Object of Preposition {AnimateType}
Command {take},{get},{pick up}, {hold}, {draw}, {hold up} : Direct Object [MovableType],[FiniteType]
Command {drop},{release},{put away}, {put down}, {return} : Direct Object [MovableType]
Command {put on},{wear} : Direct Object [MovableType]
Command {take off} : Direct Object [MovableType]
Command {say},{speak} : [words that you want to speak]
Command {talk to}, {speak to}, {speak with}, {listen to} : [creature to interact with]
Command {read} : Direct Object [ImmovableType]
Command {open},{close},{shut} : Direct Object [ImmovableType]
Command {unlock} : Direct Object [ImmovableType]
Command {touch} : Direct Object [ImmovableType]
Command {eat},{drink} : Direct Object [FiniteType]
Command {climb} : Direct Object [ImmovableType]
Command {get into} : Direct Object [ImmovableType]
Command {get out of} : Direct Object [ImmovableType] 
Command {look around}
Command {look} : [Direction]
Command {look at} : [Direct Object] [MovableType, ImmovableType, AnimateType, FiniteType]
Command {help}
Command {exit},{quit}
Command {hyper} : "0" + [three digit number], Ex: hyper 0123 
(Debugging feature - moves you to location 123)
 
- The following status interrogatives are included:

{status}
{carrying}
{holding}

- Format for data file "questdatafile.txt"

MovableType

Index/name/description/weight(f)/warmth(f)/weapon(t/f)/ranged(t/f)/elvish(t/f)/holdable(t/f)/glowsblue(t/f)/lethality(f) /[EndOfRecord]#[EndOfType]+

0/key/It is a round key./1.0/0.0/false/false/false/true/false/0.0/#
1/ring/It is a beautiful golden ring./5.0/0.0/false/false/false/true/false/0.0/#
2/dagger/A small Elvish dagger of intricate construction./5.0/0.0/true/false/true/true/true/25.0#+

AnimateType

Index/name/description/weapon(t/f)/weaponname/ranged(t/f)/elvish(t/f)/enemy(t/f)/lethality(f)/[EndOfRecord]#[EndOfType]+

0/nazgul/It is a fearsome servant of Mordor wrapped in black cloth with an invisible face and makes hideous noises./true/sword/false/false/true/25.0/#+ 

FiniteType

Index/name/description/weight(f)/edible(t/f)/food% per day(f)/drinkable(t/f)/fluid % per day(f)/healable(t/f)/healing % per day/ elvish(t/f)/poison(t/f)/energyimpovement(f)/type of food/[EndOfRecord]#[EndOfType]+

0/bread/It is freshly-baked hobbit bread./3.0/true/10.0/false/0.0/false/false/false/2.0/1/#
1/sausage/Some tasty hobbit sausage./5.0/true/20.0/false/0.0/false/false/false/1.0/3/#
2/cheese/Some freshly-made hobbit cheese./7.0/true/25.0/false/0.0/false/false/false/1.0/4/#
3/water/It is clear clean water./10.0/false/0.0/true/10.0/false/false/false/5.0/0/#+

Food types:
0 – not applicable
1 – grains
2 – fruit or vegetable
3 – meats
4 – dairy

ImmovableType

Index/name/description/readable(t/f)/inscription/openable(t/f)/open(t/f)/lockable(t/f)/locked(t/f)/unlockswithkey(t/f)/key/unlockswithspell(t/f)/climbable(t/f)/boat(t/f)/[EndOfRecord]#[EndOfType]+

0/door/It is a round wooden hobbit-style door./false//true/false/false/false/false/-1/false/false/false/#
1/window/It is a round glass window looking out towards other hobbit holes, flowers, and down the hill./false//false/true/true/true/0/false/false/false/#
2/window/It is a small window looking west out over a garden path and running down the hill towards a lane./false//true/false/true/true/true/0/false/false/false/#+

InteractType

Talkindex/friendinfo/enemyinfo1/enemyinfo2/ringinfo/#

Location

Index/spell/temperature(f)/largearea(t/f)/description/look north/look south/look east/look west/look up/look down/NN/newindex/time(f)/energy(f)/spell(t/f)/ boat(t/f)/roap(t/f)/ring(t/f)/viapassage(t/f)/ reserve1(t/f)/reserve2(t/f)/via index/[EndOfField]ZZ/[ImmovableType]index/name/[EndOfField] ZZ/[FiniteType]index/name/[EndOfField]ZZ/[MovableType]index/name/[EndOfField]ZZ/[AnimateType]index/percentage(f)/name/talkindex/[EndOfField]ZZ/[EndOfRecord]#
0/-1/17/false/You are in hallway of a cozy hobbit hole.  A round door opens to the south, and a passage runs north further into the hole./You see a passageway running further into the dwelling./You see a round door./You see the walls of the hole, covered with wood panels./ You see the walls of the hole, covered with wood panels./You see the wooden ceiling of the hobbit hole./You are standing on a wooden floor./NN/1/0.1/1/false/false/false/false/false/false/false/-1/SS/3/0.1/1/false/false/false/false/true/false/false/0/OO/3/0.1/1/false/false/false/false/true/false/false/0/ZZ/0/door/ZZ/ZZ/ZZ/#
1/-1/19/false/You are in a kitchen.  On the table there is bread, sausage, and cheese.  There is also water.  A passage leads south into a hallway.  A window looks east.  To the west a passage leads further into the hole./You see the brick wall of the kitchen./You see a passageway leading to a hall./A window looks out  - you can see flowers, trees, and a path winding down towards the village of Hobbiton./You see a passage that goes into a dim room./You see the wooden ceiling of the kitchen./You see the brick floor of the kitchen./SS/0/0.1/1/false/false/false/false/false/false/false/-1/EE/3/0.1/1/false/false/false/false/true/false/false/1/WW/2/0.1/1/false/false/false/false/false/false/false/-1/ZZ/0/table/1/window/ZZ/0/bread/1/sausage/2/cheese/3/water/ZZ/ZZ/ZZ/#
2/-1/20/false/You are in the bedroom.  A passage runs to the east into the kitchen. To the west there is a small window./You see empty bookshelves where Bilbo used to keep his books./You see the wooden walls of the bedroom./You see a passage running east into the kitchen./You see a small window./You see the wooden ceiling of the bedroom./You see the wooden floor of the bedroom./EE/1/0.1/1/false/false/false/false/false/false/false/-1/WW/7/0.1/1/false/false/false/false/true/false/false/2/ZZ/2/window/ZZ/1/ring/2/dagger/ZZ/ZZ/#
3/-1/15/false/You are on the grass lawn in front of Bag End.  The main path runs south and down the hill towards many hobbit homes and the village of Bywater. A smaller path runs towards the northwest and into Bilbos garden./You see the front door of Bilbo Baggins home, Bag End./You see a path winding down the hill, past flowers, trees, and hobbit homes.  The village of Bywater, the party tree, and a mill can be seen, with green fields in the distance./You see the green fields and pastures of Hobbiton./You see a garden.  A lane appears to run down the backside of the hill towards a brook./You see the home of Bilbo built into the top of the hill under grass./A path runs down the hill to Bywater./SS/4/0.2/2/false/false/false/false/false/false/false/-1/DN/4/0.2/2/false/false/false/false/false/false/false/-1/NW/7/0.2/2/false/false/false/false/false/false/false/-1/ZZ/ZZ/ZZ/ZZ/ZZ/#

Shortcomings in this beta version:

- No way to save status and restart later
