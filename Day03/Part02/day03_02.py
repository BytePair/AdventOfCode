def calculate_neighbors(x, y, working_grid):
    """ calculates and returns the total of neighboring squares """
    running_total = working_grid[x][y]
    running_total += working_grid[x][y+1]
    running_total += working_grid[x][y-1]
    running_total += working_grid[x-1][y+1]
    running_total += working_grid[x-1][y]
    running_total += working_grid[x-1][y-1]
    running_total += working_grid[x+1][y+1]
    running_total += working_grid[x+1][y]
    running_total += working_grid[x+1][y-1]
    return running_total


def print_grid(gridy):
    for g in gridy:
        print(g)


# get number from input file
target = int(open("input.txt", "r").read())
print("Target:", target)

# find max possible grid size
max_grid_size = 1
while target > max_grid_size*max_grid_size:
    max_grid_size += 2
    print(max_grid_size)

# create the grid
grid = [[0] * max_grid_size for i in range(max_grid_size)]

# keep track of the current number so we know when it exceeds the target
current_number = 1

# fill in first number
current_node = (max_grid_size//2, max_grid_size//2)
grid[current_node[0]][current_node[1]] = current_number

loop_size = 1

while current_number < target:

    loop_size += 2
    print("Loop Size:", loop_size)

    # start at node right of last updated
    current_node = (current_node[0], current_node[1] + 1)

    # right - move up right column
    for i in range(loop_size - 1):
        print("Current Node:", current_node)
        grid[current_node[0]][current_node[1]] = current_number = calculate_neighbors(current_node[0], current_node[1], grid)
        # break out early if ans found
        if current_number > target:
            break
        # dont move on last loop
        if i < loop_size - 2:
            current_node = (current_node[0] - 1, current_node[1])

    # break out early if ans found
    if current_number > target:
        break

    # top - move left on top row
    current_node = (current_node[0], current_node[1] - 1)
    for i in range(loop_size - 1):
        grid[current_node[0]][current_node[1]] = current_number = calculate_neighbors(current_node[0], current_node[1], grid)
        # break out early if ans found
        if current_number > target:
            break
        # dont move on last loop
        if i < loop_size - 2:
            current_node = (current_node[0], current_node[1] - 1)

    # break out early if ans found
    if current_number > target:
        break

    # left - move down left column
    current_node = (current_node[0] + 1, current_node[1])
    for i in range(loop_size - 1):
        grid[current_node[0]][current_node[1]] = current_number = calculate_neighbors(current_node[0], current_node[1], grid)
        # break out early if ans found
        if current_number > target:
            break
        # dont move on last loop
        if i < loop_size - 2:
            current_node = (current_node[0] + 1, current_node[1])

    # break out early if ans found
    if current_number > target:
        break

    # bottom - move right on bottom row
    current_node = (current_node[0], current_node[1] + 1)
    for i in range(loop_size - 1):
        grid[current_node[0]][current_node[1]] = current_number = calculate_neighbors(current_node[0], current_node[1], grid)
        # break out early if ans found
        if current_number > target:
            break
        # dont move on last loop
        if i < loop_size - 2:
            current_node = (current_node[0], current_node[1] + 1)


print_grid(grid)
print("Answer:", current_number)
