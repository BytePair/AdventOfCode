input_file = open("input.txt", "r")
goal = int(input_file.read())
input_file.close()

print("Goal:", goal)

# find minimum square size
size = 1
while (size*size < goal):
    size += 2
print("Size Required:", size)

# start at max tile in biggest required ring
current_tile = size*size

# tuple to hold coordinates of exit
exit_loc = (size // 2, size // 2)

# tuple to hold found location
# will stay (0, 0) if goal is 1  
found_loc = (0, 0)

# look in bottom row
for y in range(size - 1, 0, -1):
    #print("Current tile:", current_tile)
    if current_tile == goal:
        found_loc = (size - 1, y)
    current_tile -= 1

# look in left row
for x in range(size - 1, 0, -1):
    if current_tile == goal:
        found_loc = (x, 0)
    current_tile -= 1

# look in top row
for y in range(size - 1):
    if current_tile == goal:
        found_loc = (0, y)
    current_tile -= 1

# look in right row
for x in range(size - 1):
    if current_tile == goal:
        found_loc = (x, size - 1)
    current_tile -= 1


print("Found Location:", found_loc)

# find required movement
v_movement = found_loc[0] - exit_loc[0]
if (v_movement < 0): v_movement = v_movement // -1
h_movement = found_loc[1] - exit_loc[0]
if (h_movement < 0): h_movement = h_movement // -1
print("Manhattan Distance:", v_movement + h_movement)
