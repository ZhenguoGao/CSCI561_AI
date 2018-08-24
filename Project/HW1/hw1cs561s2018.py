import sys
f=open('Wechatinput7.txt', 'r')
lines=f.readlines() 
value = []
state =  [0 for i in range(8)] 
# print state
player = lines[0].split()
player = player[0]
print player
lines.pop(0)
al = lines[0].split()
al = al[0]
print al
lines.pop(0)
depth = lines[0].split()
depth = int(depth[0])
totalDepth = depth
print depth
lines.pop(0)
for i in range(8):
  state[i] = lines[0].split(',')
  lines.pop(0)

if (player == "Star"):
  for i in range(8):
    for j in range(8):
      if (state[i][j][0] == "S"): 
        number = int(state[i][j][1])
        state[i][j] = number
      elif(state[i][j][0] == "C"):
        number = int(state[i][j][1])
        state[i][j] = number * (-1)
      else:
        state[i][j] = 0
elif (player == "Circle"):
  for i in range(8):
    for j in range(8):
      if (state[i][j][0] == "S"): 
        number = int(state[i][j][1]) 
        state[i][j] = number * (-1)
      elif(state[i][j][0] == "C"):
        number = int(state[i][j][1])
        state[i][j] = number 
      else:
        state[i][j] = 0
# print state

value = lines[0].split(',')
# print value
global totalNode
global pathX 
global pathY 
global pathX1 
global pathY1 
global firstUtilty  
pathX = -1
pathY = -1
pathX1 = -1
pathY1 = -1
firstUtilty = -1
totalNode = 1
global row
row = [ "H", "G", "F", "E", "D", "C", "B", "A" ]
global column 
column= [ "1", "2", "3", "4", "5", "6", "7", "8" ]

def miniMaxHelper(player,totalDepth,depth,value,state,end):
  global totalNode 
  global pathX 
  global pathY 
  global pathX1 
  global pathY1 
  global firstUtilty
  max1 = 0
  min1 = 0
  if (player == "Star"):
      max1 = float('-inf')
  elif (player == "Circle"):
      min1 = float('inf')

  if (depth == 0 or exitOne(state)): 
      return calUtility(state, value)
  elif (player == "Star"):
      flag = False
      
      for i in range (8):
        for j in range (8):
          if ((state[i][j] == 1) and (i - 1 >= 0) and (j - 1 >= 0) and (state[i - 1][j - 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j - 1)
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j - 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j - 1, i, j)

          if ((state[i][j] == 1) and (i - 1 >= 0) and (j + 1 < 8) and (state[i - 1][j + 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j + 1)
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j + 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j + 1, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j - 2 >= 0) and (state[i - 1][j - 1] == -1 ) and(state[i - 2][j - 2] != -1) and (i - 2 == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j -2)
              state[i - 1][j - 1] = 0
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2 
                  pathY = j - 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j - 1] = -1
              Switch(state, i - 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == -1) and (state[i -2][j + 2] != -1) and (i -2 == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0;
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j + 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j + 1] = -1
              Switch(state, i - 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j - 2 >= 0 ) and (state[i - 1][j - 1] == -1) and (state[i -2][j -2] == 0) and (i -2 != 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j - 2)
              state[i - 1][j - 1] = 0
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j - 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j - 1] = -1 
              Switch(state, i - 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == -1) and (state[i - 2][j + 2] == 0) and (i - 2 != 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j + 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j + 1] = -1
              Switch(state, i - 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i == 1) and (i - 1 >= 0) and(j - 1 >= 0) and (state[i - 1][j - 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j - 1)
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j - 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j - 1, i, j)

          if ((state[i][j] == 1 and (i == 1)) and (i - 1 >= 0) and (j + 1 < 8 ) and (state[i - 1][j + 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j + 1)
              k = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j + 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j + 1, i, j)

      if (flag == False):
        if (depth == totalDepth):
          Output=open("output.txt", "w")
          Output.write("pass" + "\n")
          Output.write(str(calUtility(state, value)))
          Output.close()
          firstUtilty = calUtility(state, value)
          print "pass"
          print "After the first move utility:" + str(calUtility(state, value))
        
        totalNode = totalNode + 1
        if (end == False):
          max1 = miniMaxHelper("Circle", totalDepth, depth - 1, value, state, True)
        else:
          max1 = calUtility(state, value)

      if (depth == totalDepth and pathX1 >= 0 and pathY1 >= 0 and pathX >= 0 and pathY >= 0):
          Output=open("output.txt", "w")
          Output.close()
          print "The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX] + column[pathY]
          print "After the first move utility: " + str(firstUtilty)
  
  elif (player == "Circle"):
      flag = False
      for i in range (6,-1,-1):
        for j in range (8):
          if ((state[i][j] == -1) and (i + 1 >= 0) and (j - 1 >= 0) and (state[i + 1][j - 1] == 0)):
                totalNode = totalNode + 1
                flag = True
                SwitchTwo (state, i, j, i + 1, j - 1)
                min1 = min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False))
                SwitchTwo (state,i + 1,j - 1,i,j)

          if ((state[i][j] == -1) and (i + 1 >= 0) and (j + 1 < 8) and (state[i + 1][j + 1] == 0)):         
                totalNode = totalNode + 1
                flag = True
                SwitchTwo(state, i, j, i + 1, j + 1)
                min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False)) 
                SwitchTwo(state, i + 1, j + 1, i, j)

          if ((state[i][j] == -1) and (i + 2 < 8) and (j - 2 >= 0) and (state[i + 1][j - 1] == 1 ) and (state[i + 2][j - 2] != 1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j -2)
              state[i + 1][j - 1] = 0
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False)) 
              state[i + 1][j - 1] = -1
              SwitchTwo(state, i + 2, j - 2, i, j)

          if ((state[i][j] == -1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0;
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False))
              state[i + 1][j + 1] = -1
              SwitchTwo(state, i + 2, j + 2, i, j)

          if ((state[i][j] == -1) and (i + 2 < 8) and (j - 2 >= 0 ) and (state[i + 1][j - 1] == 1) and (state[i + 2][j -2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j - 2)
              state[i + 1][j - 1] = 0
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False))
              state[i + 1][j - 1] = -1 
              SwitchTwo(state, i + 2, j - 2, i, j)

          if ((state[i][j] == -1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == 1) and (state[i + 2][j + 2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False)) 
              state[i + 1][j + 1] = -1
              SwitchTwo(state, i + 2, j + 2, i, j)

          if ((state[i][j] == -1) and (i == 6) and (i + 1 < 8) and(j - 1 >=0) and (state[i + 1][j - 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 1, j - 1)
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i + 1, j - 1, i, j)

          if ((state[i][j] == -1 and (i == 6)) and (i + 1 < 8) and (j + 1 < 8 ) and (state[i + 1][j + 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 1, j + 1)
              min1 =min(min1,miniMaxHelper("Star", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i + 1, j + 1, i, j)

      if (flag == False):
          totalNode = totalNode + 1
          if (end == False):
            min1 = miniMaxHelper("Star", totalDepth, depth - 1, value, state, True)
          else:
            min1 = calUtility(state, value)
  if (player == "Star"):
    return max1
  return min1  

def miniMaxHelperTwo(player,totalDepth,depth,value,state,end):
  global totalNode 
  global pathX 
  global pathY 
  global pathX1 
  global pathY1 
  global firstUtilty
  max1 = 0
  min1 = 0
  if (player == "Circle"):
      max1 = float('-inf')
  elif (player == "Star"):
      min1 = float('inf')

  if (depth == 0 or exitOne(state)): 
      return calUtilityTwo(state, value)
  elif (player == "Star"):
      flag = False
      for i in range (8):
        for j in range (8):
          if ((state[i][j] == -1) and (i - 1 >= 0) and (j - 1 >= 0) and (state[i - 1][j - 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j - 1)
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False)) 
              SwitchTwo(state, i - 1, j - 1, i, j)

          if ((state[i][j] == -1) and (i - 1 >= 0) and (j + 1 < 8) and (state[i - 1][j + 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j + 1)
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i - 1, j + 1, i, j)

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j - 2 >= 0) and (state[i - 1][j - 1] == 1 ) and(state[i - 2][j - 2] != 1) and (i - 2 == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j -2)
              state[i - 1][j - 1] = 0
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j - 1] = 1
              SwitchTwo(state, i - 2, j - 2, i, j)

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == 1) and (state[i -2][j + 2] != 1) and (i -2 == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0;
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j + 1] = 1
              SwitchTwo(state, i - 2, j + 2, i, j)

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j - 2 >= 0 ) and (state[i - 1][j - 1] == 1) and (state[i -2][j -2] == 0) and (i -2 != 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j - 2)
              state[i - 1][j - 1] = 0
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j - 1] = 1 
              SwitchTwo(state, i - 2, j - 2, i, j)

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == 1) and (state[i - 2][j + 2] == 0) and (i - 2 != 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j + 2)
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j + 1] = 1
              SwitchTwo(state, i - 2, j + 2, i, j)

          if ((state[i][j] == -1) and (i == 1) and (i - 1 >= 0) and(j - 1 >= 0) and (state[i - 1][j - 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j - 1)
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i - 1, j - 1, i, j)

          if ((state[i][j] == -1 and (i == 1)) and (i - 1 >= 0) and (j + 1 < 8 ) and (state[i - 1][j + 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j + 1)
              min1 = min(min1,miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i - 1, j + 1, i, j)

      if (flag == False):
        totalNode = totalNode + 1
        if (end == False):
          min1 = miniMaxHelperTwo("Circle", totalDepth, depth - 1, value, state, True)
        else:
          min1 = calUtilityTwo(state, value)

  elif (player == "Circle"):
      flag = False
      for i in range (6,-1,-1):
        for j in range (8):
          if ((state[i][j] == 1) and (i + 1 >= 0) and (j - 1 >= 0) and (state[i + 1][j - 1] == 0)):
                totalNode = totalNode + 1
                flag = True
                Switch(state, i, j, i + 1, j - 1)
                k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
                if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j - 1
                  firstUtilty = calUtilityTwo(state, value)
                Switch (state,i + 1,j - 1,i,j)

          if ((state[i][j] == 1) and (i + 1 >= 0) and (j + 1 < 8) and (state[i + 1][j + 1] == 0)):         
                totalNode = totalNode + 1
                flag = True
                Switch(state, i, j, i + 1, j + 1)
                k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
                if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j + 1
                  firstUtilty = calUtilityTwo(state, value)
                Switch(state, i + 1, j + 1, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j - 2 >= 0) and (state[i + 1][j - 1] == -1 ) and (state[i + 2][j - 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j -2)
              state[i + 1][j - 1] = 0
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j - 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j - 1] = -1
              Switch(state, i + 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0;
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j + 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j + 1] = -1
              Switch(state, i + 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j - 2 >= 0 ) and (state[i + 1][j - 1] == -1) and (state[i + 2][j -2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j - 2)
              state[i + 1][j - 1] = 0
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j - 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j - 1] = -1 
              Switch(state, i + 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j + 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j + 1] = -1
              Switch(state, i + 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i == 6) and (i + 1 < 8) and(j - 1 >=0) and (state[i + 1][j - 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 1, j - 1)
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j - 1
                  firstUtilty = calUtilityTwo(state, value)
              Switch(state, i + 1, j - 1, i, j)

          if ((state[i][j] == 1 and (i == 6)) and (i + 1 < 8) and (j + 1 < 8 ) and (state[i + 1][j + 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 1, j + 1)
              k = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j + 1
                  firstUtilty = calUtilityTwo(state, value)
              Switch(state, i + 1, j + 1, i, j)

      if (flag == False):
        if (depth == totalDepth):
          Output=open("output.txt", "w")
          Output.write("pass" + "\n")
          Output.write(str(calUtilityTwo(state, value)))
          Output.close()
          firstUtilty = calUtilityTwo(state, value)
          print "pass"
          print "After the first move utility:" + str(calUtilityTwo(state, value))
        totalNode = totalNode + 1
        if (end == False):
            max1 = miniMaxHelperTwo("Star", totalDepth, depth - 1, value, state, True)
        else:
            max1 = calUtilityTwo(state, value)
      if (depth == totalDepth and pathX1 >= 0 and pathY1 >= 0 and pathX >= 0 and pathY >= 0):
              Output=open("output.txt", "w")
              Output.close()
              print "The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX] + column[pathY]
              print "After the first move utility: " + str(firstUtilty)
  if (player == "Circle"):
    return max1
  return min1  

def miniMaxHelperThree(player,totalDepth,depth,value,state,globalMax,end):
  global totalNode 
  global pathX 
  global pathY 
  global pathX1 
  global pathY1 
  global firstUtilty
  max1 = 0
  min1 = 0
  if (player == "Star"):
      max1 = float('-inf')
  elif (player == "Circle"):
      min1 = float('inf')

  if (depth == 0 or exitOne(state)): 
      return calUtility(state, value)
  elif (player == "Star"):
      flag = False
      
      for i in range (8):
        for j in range (8):

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j - 2 >= 0) and (state[i - 1][j - 1] == -1 ) and(state[i - 2][j - 2] != -1) and (i - 2 == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j -2)
              state[i - 1][j - 1] = 0
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2 
                  pathY = j - 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j - 1] = -1
              Switch(state, i - 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == -1) and (state[i -2][j + 2] != -1) and (i -2 == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0;
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j + 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j + 1] = -1
              Switch(state, i - 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j - 2 >= 0 ) and (state[i - 1][j - 1] == -1) and (state[i -2][j -2] == 0) and (i -2 != 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j - 2)
              state[i - 1][j - 1] = 0
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j - 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j - 1] = -1 
              Switch(state, i - 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == -1) and (state[i - 2][j + 2] == 0) and (i - 2 != 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 2
                  pathY = j + 2
                  firstUtilty = calUtility(state, value)
              state[i - 1][j + 1] = -1
              Switch(state, i - 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i - 1 >= 0) and (j - 1 >= 0) and (state[i - 1][j - 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j - 1)
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j - 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j - 1, i, j)

          if ((state[i][j] == 1) and (i - 1 >= 0) and (j + 1 < 8) and (state[i - 1][j + 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j + 1)
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j + 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j + 1, i, j)

          

          if ((state[i][j] == 1) and (i == 1) and (i - 1 >= 0) and(j - 1 >= 0) and (state[i - 1][j - 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j - 1)
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state, max1,False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j - 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j - 1, i, j)

          if ((state[i][j] == 1 and (i == 1)) and (i - 1 >= 0) and (j + 1 < 8 ) and (state[i - 1][j + 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i - 1, j + 1)
              k = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i - 1
                  pathY = j + 1
                  firstUtilty = calUtility(state, value)
              Switch(state, i - 1, j + 1, i, j)

      if (flag == False):
        if (depth == totalDepth):
          Output=open("output.txt", "w")
          Output.write("pass" + "\n")
          Output.write(str(calUtility(state, value)))
          Output.close()
          firstUtilty = calUtility(state, value)
          print "pass"
          print "After the first move utility:" + str(calUtility(state, value))
        
        totalNode = totalNode + 1
        if (end == False):
          max1 = miniMaxHelperThree("Circle", totalDepth, depth - 1, value, state, globalMax,True)
        else:
          max1 = calUtility(state, value)

      if (depth == totalDepth and pathX1 >= 0 and pathY1 >= 0 and pathX >= 0 and pathY >= 0):
          Output=open("output.txt", "w")
          Output.close()
          print "The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX] + column[pathY]
          print "After the first move utility: " + str(firstUtilty)
  
  elif (player == "Circle"):
      flag = False
      for i in range (6,-1,-1):
        for j in range (8):
          if ((state[i][j] == -1) and (i + 1 >= 0) and (j - 1 >= 0) and (state[i + 1][j - 1] == 0)):
                totalNode = totalNode + 1
                flag = True
                SwitchTwo (state, i, j, i + 1, j - 1)
                min1 = min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False))
                SwitchTwo (state,i + 1,j - 1,i,j)
                if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i + 1 >= 0) and (j + 1 < 8) and (state[i + 1][j + 1] == 0)):         
                totalNode = totalNode + 1
                flag = True
                SwitchTwo(state, i, j, i + 1, j + 1)
                min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False)) 
                SwitchTwo(state, i + 1, j + 1, i, j)
                if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i + 2 < 8) and (j - 2 >= 0) and (state[i + 1][j - 1] == 1 ) and (state[i + 2][j - 2] != 1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j -2)
              state[i + 1][j - 1] = 0
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False)) 
              state[i + 1][j - 1] = -1
              SwitchTwo(state, i + 2, j - 2, i, j)
              if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0;
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False))
              state[i + 1][j + 1] = -1
              SwitchTwo(state, i + 2, j + 2, i, j)
              if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i + 2 < 8) and (j - 2 >= 0 ) and (state[i + 1][j - 1] == 1) and (state[i + 2][j -2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j - 2)
              state[i + 1][j - 1] = 0
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False))
              state[i + 1][j - 1] = -1 
              SwitchTwo(state, i + 2, j - 2, i, j)
              if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == 1) and (state[i + 2][j + 2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False)) 
              state[i + 1][j + 1] = -1
              SwitchTwo(state, i + 2, j + 2, i, j)
              if(min1 < globalMax):
                  break

          if ((state[i][j] == -1) and (i == 6) and (i + 1 < 8) and(j - 1 >=0) and (state[i + 1][j - 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 1, j - 1)
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, False))
              SwitchTwo(state, i + 1, j - 1, i, j)
              if(min1 < globalMax):
                  break

          if ((state[i][j] == -1 and (i == 6)) and (i + 1 < 8) and (j + 1 < 8 ) and (state[i + 1][j + 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i + 1, j + 1)
              min1 =min(min1,miniMaxHelperThree("Star", totalDepth, depth - 1, value, state, globalMax,False))
              SwitchTwo(state, i + 1, j + 1, i, j)
              if(min1 < globalMax):
                  break

      if (flag == False):
          totalNode = totalNode + 1
          if (end == False):
            min1 = miniMaxHelperThree("Star", totalDepth, depth - 1, value, state,globalMax, True)
          else:
            min1 = calUtility(state, value)
  if (player == "Star"):
    return max1
  return min1  

def miniMaxHelperFour(player,totalDepth,depth,value,state,globalMax,end):
  global totalNode 
  global pathX 
  global pathY 
  global pathX1 
  global pathY1 
  global firstUtilty
  max1 = 0
  min1 = 0
  if (player == "Circle"):
      max1 = float('-inf')
  elif (player == "Star"):
      min1 = float('inf')

  if (depth == 0 or exitOne(state)): 
      return calUtilityTwo(state, value)
  elif (player == "Star"):
      flag = False
      for i in range (8):
        for j in range (8):
          if ((state[i][j] == -1) and (i - 1 >= 0) and (j - 1 >= 0) and (state[i - 1][j - 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j - 1)
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state,globalMax, False)) 
              SwitchTwo(state, i - 1, j - 1, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i - 1 >= 0) and (j + 1 < 8) and (state[i - 1][j + 1] == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j + 1)
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state,globalMax, False))
              SwitchTwo(state, i - 1, j + 1, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j - 2 >= 0) and (state[i - 1][j - 1] == 1 ) and(state[i - 2][j - 2] != 1) and (i - 2 == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j -2)
              state[i - 1][j - 1] = 0
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j - 1] = 1
              SwitchTwo(state, i - 2, j - 2, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == 1) and (state[i -2][j + 2] != 1) and (i -2 == 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j + 2)
              state[i - 1][j + 1] = 0;
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j + 1] = 1
              SwitchTwo(state, i - 2, j + 2, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j - 2 >= 0 ) and (state[i - 1][j - 1] == 1) and (state[i -2][j -2] == 0) and (i -2 != 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j - 2)
              state[i - 1][j - 1] = 0
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j - 1] = 1 
              SwitchTwo(state, i - 2, j - 2, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i - 2 >= 0) and (j + 2 < 8) and (state[i - 1][j + 1] == 1) and (state[i - 2][j + 2] == 0) and (i - 2 != 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 2, j + 2)
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state, False))
              state[i - 1][j + 1] = 1
              SwitchTwo(state, i - 2, j + 2, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1) and (i == 1) and (i - 1 >= 0) and(j - 1 >= 0) and (state[i - 1][j - 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j - 1)
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state, False))
              SwitchTwo(state, i - 1, j - 1, i, j)
              if(min1 < globalMax):
                break

          if ((state[i][j] == -1 and (i == 1)) and (i - 1 >= 0) and (j + 1 < 8 ) and (state[i - 1][j + 1] < 0)):
              totalNode = totalNode + 1
              flag = True
              SwitchTwo(state, i, j, i - 1, j + 1)
              min1 = min(min1,miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state,globalMax, False))
              SwitchTwo(state, i - 1, j + 1, i, j)
              if(min1 < globalMax):
                break

      if (flag == False):
        totalNode = totalNode + 1
        if (end == False):
          min1 = miniMaxHelperFour("Circle", totalDepth, depth - 1, value, state,globalMax, True)
        else:
          min1 = calUtilityTwo(state, value)

  elif (player == "Circle"):
      flag = False
      for i in range (6,-1,-1):
        for j in range (8):
          if ((state[i][j] == 1) and (i + 2 < 8) and (j - 2 >= 0) and (state[i + 1][j - 1] == -1 ) and (state[i + 2][j - 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j -2)
              state[i + 1][j - 1] = 0
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j - 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j - 1] = -1
              Switch(state, i + 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] != -1) and (i + 2 == 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0;
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j + 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j + 1] = -1
              Switch(state, i + 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j - 2 >= 0 ) and (state[i + 1][j - 1] == -1) and (state[i + 2][j -2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j - 2)
              state[i + 1][j - 1] = 0
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state, max1,False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j - 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j - 1] = -1 
              Switch(state, i + 2, j - 2, i, j)

          if ((state[i][j] == 1) and (i + 2 < 8) and (j + 2 < 8) and (state[i + 1][j + 1] == -1) and (state[i + 2][j + 2] == 0) and (i + 2 != 7)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 2, j + 2)
              state[i + 1][j + 1] = 0
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state, max1,False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 2
                  pathY = j + 2
                  firstUtilty = calUtilityTwo(state, value)
              state[i + 1][j + 1] = -1
              Switch(state, i + 2, j + 2, i, j)

          if ((state[i][j] == 1) and (i + 1 >= 0) and (j - 1 >= 0) and (state[i + 1][j - 1] == 0)):
                totalNode = totalNode + 1
                flag = True
                Switch(state, i, j, i + 1, j - 1)
                k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
                if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j - 1
                  firstUtilty = calUtilityTwo(state, value)
                Switch (state,i + 1,j - 1,i,j)

          if ((state[i][j] == 1) and (i + 1 >= 0) and (j + 1 < 8) and (state[i + 1][j + 1] == 0)):         
                totalNode = totalNode + 1
                flag = True
                Switch(state, i, j, i + 1, j + 1)
                k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
                if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j + 1
                  firstUtilty = calUtilityTwo(state, value)
                Switch(state, i + 1, j + 1, i, j)

          

          if ((state[i][j] == 1) and (i == 6) and (i + 1 < 8) and(j - 1 >=0) and (state[i + 1][j - 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 1, j - 1)
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j - 1
                  firstUtilty = calUtilityTwo(state, value)
              Switch(state, i + 1, j - 1, i, j)

          if ((state[i][j] == 1 and (i == 6)) and (i + 1 < 8) and (j + 1 < 8 ) and (state[i + 1][j + 1] > 0)):
              totalNode = totalNode + 1
              flag = True
              Switch(state, i, j, i + 1, j + 1)
              k = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,max1, False)
              if (max1 < k):
                  max1 = k
                  pathX1 = i
                  pathY1 = j
                  pathX = i + 1
                  pathY = j + 1
                  firstUtilty = calUtilityTwo(state, value)
              Switch(state, i + 1, j + 1, i, j)

      if (flag == False):
        if (depth == totalDepth):
          Output=open("output.txt", "w")
          Output.write("pass" + "\n")
          Output.write(str(calUtilityTwo(state, value)))
          Output.close()
          firstUtilty = calUtilityTwo(state, value)
          print "pass"
          print "After the first move utility:" + str(firstUtilty)
          totalNode = totalNode + 1
        if (end == False):
            max1 = miniMaxHelperFour("Star", totalDepth, depth - 1, value, state,globalMax, True)
        else:
            max1 = calUtilityTwo(state, value)
      if (depth == totalDepth and pathX1 >= 0 and pathY1 >= 0 and pathX >= 0 and pathY >= 0):
              Output=open("output.txt", "w")
              Output.close()
              print "The first step:" + "start: " + row[pathX1] + column[pathY1] + " end: " + row[pathX] + column[pathY]
              print "After the first move utility: " + str(firstUtilty)
  if (player == "Circle"):
    return max1
  return min1  





     



     


def exitOne(state):
  one = False
  two = False
  for i in range (8):
    for j in range(8):
      if(state[i][j] > 0):
        one = True
      elif (state[i][j] < 0):
        two = True

  if (one ^ two ):
    # print "true"
    return True
  # print "false"
  return False
# exitOne(state)

def Switch (state,X1,Y1,X2,Y2):
  state[X1][Y1] = state[X1][Y1] - 1
  state[X2][Y2] = state[X2][Y2] + 1
  return

def SwitchTwo (state,X1,Y1,X2,Y2):
  state[X1][Y1] = state[X1][Y1] + 1
  state[X2][Y2] = state[X2][Y2] - 1
  return
  

def calUtility(state,value):
  sum = 0
  for i in range(8):
    for j in range(8):
      if (state[i][j] > 0):
        sum = state[i][j] * int(value[7 - i]) + sum
      else:
        sum = state[i][j] * int(value[i]) + sum
  return sum


def calUtilityTwo(state,value):
  sum = 0
  for i in range(8):
    for j in range(8):
      if (state[i][j] > 0):
        sum = state[i][j] * int(value[i]) + sum
      else:
        sum = state[i][j] * int(value[7 - i]) + sum
  return sum


if (player == 'Star' and al == 'MINIMAX'):
  solution = miniMaxHelper(player,totalDepth,depth,value,state,False)
elif(player == 'Circle' and al == 'MINIMAX'):
  solution = miniMaxHelperTwo(player,totalDepth,depth,value,state,False)
elif(player == 'Star' and al == 'ALPHABETA'):
  solution = miniMaxHelperThree(player,totalDepth,depth,value,state,float('-inf'),False)
elif(player == 'Circle' and al == 'ALPHABETA'):
  solution = miniMaxHelperFour(player,totalDepth,depth,value,state,float('-inf'),False)


Output=open("output.txt", "w")
if(pathX1>=0 and pathY1>=0 and pathX >=0 and pathY>=0):
    Output.write(row[pathX1] + column[pathY1] +"-"+ row[pathX] + column[pathY]+"\n")
else:
    Output.write("pass"+"\n")
Output.write(str(firstUtilty)+"\n")
Output.write(str(solution) + "\n")
Output.write(str(totalNode))
Output.close()

