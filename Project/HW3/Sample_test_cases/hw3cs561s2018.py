import sys
import numpy as np
import time
start = time.clock()
f=open('input4.txt', 'r')
lines=f.readlines() 
lines1 = lines[0].split(',')
rows = int(lines1[0])
columns = int(lines1[1])
# print(rows)
# print(columns)
lines.pop(0)
global grid 
grid= [ [ 0 for i in range(columns) ] for j in range(rows) ]
global state
state= [ [ 0 for i in range(columns) ] for j in range(rows) ]
wallsNumber = int(lines[0]) 
print(wallsNumber)
lines.pop(0)
for i in range (wallsNumber) :
  lineswall = lines[0].split(',')
  grid[rows - int(lineswall[0])][int(lineswall[1]) - 1 ] = 1
  state[rows - int(lineswall[0]) ][int(lineswall[1]) - 1] = None
  lines.pop(0)

terminalNum = int(lines[0])
print terminalNum
lines.pop(0)
for  i in range (terminalNum): 
  linesterminal = lines[0].split(',')
  grid[rows - int(linesterminal[0])][int(linesterminal[1]) - 1] = 2
  state[rows - int(linesterminal[0])][int(linesterminal[1]) - 1] = float(linesterminal[2])
  lines.pop(0)
# print(state)
# print(grid)
probablitylines = lines[0].split(',')
global pWalk
global pRun
pWalk = float(probablitylines[0])
pRun = float(probablitylines[1])
print(pWalk)
print(pRun)
lines.pop(0)
rewalklines = lines[0].split(',')
global rWalk
global rRun
rWalk = float(rewalklines[0])
rRun =  float(rewalklines[1])
print(rWalk)
print(rRun)
lines.pop(0)
global factor
factor = float(lines[0])
print(factor)

error = 0.00
global theta
theta = 0.00
print(theta)
global answer
answer = [ [ "" for i in range(columns) ] for j in range(rows) ]

def iterative(state,grid,rows,columns,pWalk,pRun,factor,rWalk,rRun,theta,answer):
  
  storage = [ [ 0 for i in range(16) ] for j in range(rows * columns) ]

  for i in range (rows):
    for j in range(columns):
      k = i * columns + j
      if (grid[i][j] == 0):
        # walk up
        if (i - 1 >= 0 and grid[i - 1][j] != 1):
          storage[k][0] = i - 1
          storage[k][1] = j
        else :
          storage[k][0] = i
          storage[k][1] = j
        # walk down
        if (i + 1 < rows and grid[i + 1][j] != 1):
          storage[k][2] = i + 1
          storage[k][3] = j
        else :
          storage[k][2] = i
          storage[k][3] = j

        # walk left
        if (j - 1 >= 0 and grid[i][j - 1] != 1):
          storage[k][4] = i
          storage[k][5] = j - 1
        else :
          storage[k][4] = i
          storage[k][5] = j

        # walk right
        if (j + 1 < columns and grid[i][j + 1] != 1):
          storage[k][6] = i
          storage[k][7] = j + 1
        else :
          storage[k][6] = i
          storage[k][7] = j

        # run up 
        if (i - 2 >= 0 and grid[i - 1][j] != 1 and grid[i - 2][j] != 1):
          storage[k][8] = i - 2
          storage[k][9] = j
        else :
          storage[k][8] = i
          storage[k][9] = j

        # run down
        if (i + 2 < rows and grid[i + 1][j] != 1 and grid[i + 2][j] != 1):
          storage[k][10] = i + 2
          storage[k][11] = j
        else :
          storage[k][10] = i
          storage[k][11] = j

        # run left
        if (j - 2 >= 0 and grid[i][j - 1] != 1 and grid[i][j - 2] != 1):
          storage[k][12] = i 
          storage[k][13] = j - 2
        else :
          storage[k][12] = i
          storage[k][13] = j

        # run right
        if (j + 2 < columns and grid[i][j + 1] != 1 and grid[i][j + 2] != 1):
          storage[k][14] = i
          storage[k][15] = j + 2
        else :
          storage[k][14] = i
          storage[k][15] = j
  # count = 0

  storage1 = tuple(storage)
  while (1):
    # count = count + 1
    difference = 0  
    for i in range (rows - 1, -1, -1):
      for j in range (columns):
        k = i * columns + j
        if (grid[i][j] == 0):
          original = state[i][j]
          utilityWalkUp = rWalk + factor * (pWalk * state[storage1[k][0]][storage1[k][1]]  + 0.5 * (1 - pWalk) * state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk) * state[storage1[k][6]][storage1[k][7]])
          utilityWalkDown = rWalk + factor * (pWalk * state[storage1[k][2]][storage1[k][3]] + 0.5 * (1 - pWalk)*state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk) *state[storage1[k][6]][storage1[k][7]])
          utilityWalkLeft = rWalk + factor * (pWalk * state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk)* state[storage1[k][0]][storage1[k][1]] + 0.5 * (1 - pWalk) * state[storage1[k][2]][storage1[k][3]])
          utilityWalkRight = rWalk + factor * (pWalk * state[storage1[k][6]][storage1[k][7]] + 0.5 * (1 - pWalk)* state[storage1[k][0]][storage1[k][1]] + 0.5 * (1 - pWalk) * state[storage1[k][2]][storage1[k][3]])
          utilityRunUp = rRun + factor * (pRun *state[storage1[k][8]][storage1[k][9]] + 0.5 * (1 - pRun)* state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun) * state[storage1[k][14]][storage1[k][15]])
          utilityRunDown = rRun + factor * (pRun * state[storage1[k][10]][storage1[k][11]] + 0.5 * (1 - pRun)* state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun) * state[storage1[k][14]][storage1[k][15]])
          utilityRunLeft = rRun + factor * (pRun * state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun)* state[storage1[k][8]][storage1[k][9]] + 0.5 * (1 - pRun) * state[storage1[k][10]][storage1[k][11]])
          utilityRunRight = rRun + factor * (pRun * state[storage1[k][14]][storage1[k][15]] + 0.5 * (1 - pRun)* state[storage1[k][8]][storage1[k][9]]+ 0.5 * (1 - pRun) * state[storage1[k][10]][storage1[k][11]])
          
          state[i][j] = max(utilityWalkUp,utilityWalkDown,utilityWalkLeft,utilityWalkRight,utilityRunUp,utilityRunDown,utilityRunLeft,utilityRunRight)
          if (abs(state[i][j] - original) > difference ): 
            difference = abs(state[i][j] - original) 

    if (difference == theta):
      # print(count)
      break

  for i in range (rows - 1, -1, -1):
      for j in range (columns):
        k = i * columns + j
        if (grid[i][j] == 0):
          original = state[i][j]
          utilityWalkUp = rWalk + factor * (pWalk * state[storage1[k][0]][storage1[k][1]]  + 0.5 * (1 - pWalk) * state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk) * state[storage1[k][6]][storage1[k][7]])
          utilityWalkDown = rWalk + factor * (pWalk * state[storage1[k][2]][storage1[k][3]] + 0.5 * (1 - pWalk)*state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk) *state[storage1[k][6]][storage1[k][7]])
          utilityWalkLeft = rWalk + factor * (pWalk * state[storage1[k][4]][storage1[k][5]] + 0.5 * (1 - pWalk)* state[storage1[k][0]][storage1[k][1]] + 0.5 * (1 - pWalk) * state[storage1[k][2]][storage1[k][3]])
          utilityWalkRight = rWalk + factor * (pWalk * state[storage1[k][6]][storage1[k][7]] + 0.5 * (1 - pWalk)* state[storage1[k][0]][storage1[k][1]] + 0.5 * (1 - pWalk) * state[storage1[k][2]][storage1[k][3]])
          utilityRunUp = rRun + factor * (pRun *state[storage1[k][8]][storage1[k][9]] + 0.5 * (1 - pRun)* state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun) * state[storage1[k][14]][storage1[k][15]])
          utilityRunDown = rRun + factor * (pRun * state[storage1[k][10]][storage1[k][11]] + 0.5 * (1 - pRun)* state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun) * state[storage1[k][14]][storage1[k][15]])
          utilityRunLeft = rRun + factor * (pRun * state[storage1[k][12]][storage1[k][13]] + 0.5 * (1 - pRun)* state[storage1[k][8]][storage1[k][9]] + 0.5 * (1 - pRun) * state[storage1[k][10]][storage1[k][11]])
          utilityRunRight = rRun + factor * (pRun * state[storage1[k][14]][storage1[k][15]] + 0.5 * (1 - pRun)* state[storage1[k][8]][storage1[k][9]]+ 0.5 * (1 - pRun) * state[storage1[k][10]][storage1[k][11]])
          
          kkk = max(utilityWalkUp,utilityWalkDown,utilityWalkLeft,utilityWalkRight,utilityRunUp,utilityRunDown,utilityRunLeft,utilityRunRight)
          
          if (kkk== utilityWalkUp) : 
            answer[i][j] = "Walk Up"
          elif (kkk== utilityWalkDown):
            answer[i][j] = "Walk Down"
          elif (kkk == utilityWalkLeft):
            answer[i][j] = "Walk Left"
          elif (kkk == utilityWalkRight):
            answer[i][j] = "Walk Right"
          elif (kkk == utilityRunUp):
            answer[i][j] = "Run Up"
          elif (kkk == utilityRunDown):
            answer[i][j] = "Run Down"
          elif (kkk == utilityRunLeft):
            answer[i][j] = "Run Left"
          elif (kkk == utilityRunRight):
            answer[i][j] = "Run Right"

        elif (grid[i][j] == 1):
          answer[i][j] = "None"
        elif (grid[i][j] == 2):
          answer[i][j] = "Exit"



# answer = [ [ "" for i in range(columns) ] for j in range(rows) ]
# print(answer)

iterative(state,grid,rows,columns,pWalk,pRun,factor,rWalk,rRun,theta,answer)

Output=open("output.txt", "w")
for i in range (rows):
  for j in range (columns):
    if (j == columns - 1 ):
      Output.write(answer[i][j] + "\n")
    else:
      Output.write(answer[i][j] + "" + ",")
Output.close()


print(time.clock() - start)
        


  



