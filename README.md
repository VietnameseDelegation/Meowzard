# Important info
there has to be atleast wave.csv in the res/waves <br>
there has to be atleast one pattern.csv that will be loaded into a enemy (in the file) <br>
the jar file has to be in the root directory with all other folders (except javadocs and unit test)
# Controls
W - up <br>
S - down <br>
A - left<br>
D - right<br>
L - Shoot<br>
esc - pause<br>
# Adding waves
to add a wave you have to name it wave+numberBefore+1.csv for example is the wave1.csv last one and you want to add a new one, so the new file is named wave2.csv (if there isnt any, wave0.csv is the first one)
1)	Choice – can be ghost, octopus, bug, skeleton.
2)	X – where the enemy spawns on the x-axis
3)	Y – where the enemy spawns on the y-axis
4)	Speed – how fast will the enemy be.
5)	shootCooldown – how fast will the enemy shoot
6)	health – how many bullets can the enemy take before dying.
7)	patternFilePath – file path of the pattern they will be walking in 
8)	scoreAfterDefeat – how many score will you get after defeating one enemy.
## Example: <br>
choice,x,y,speed,shootCooldown,health,patternFilePath,scoreAfterDefeat <br>
ghost,1000,100,5,50,13,res/Coords/Pattern.csv,20 <br>
bug,1050,100,5,80,3,res/Coords/trianglePattern.csv,10 <br>
ghost,1100,100,5,100,2,res/Coords/hourGlassPattern.csv,10 <br>
skeleton,1100,100,3,100,2,res/Coords/rhombus.csv,50 <br>
octopus,1200,300,4,30,4,res/Coords/rhombus.csv,30 <br>
P.S i wave 2 - 5.csv is broken intentionally
