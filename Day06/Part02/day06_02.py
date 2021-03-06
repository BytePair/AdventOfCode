def getBlocks(filename):
    file = open(filename, 'r')
    return [int(i) for i in file.read().split()]


if __name__ == '__main__':

    # get initial distribution of blocks
    blocks = getBlocks('input.txt')

    # keep track of past patterns
    past_blocks = []

    # keep redistributing blocks until a pattern repeats
    loops = 0
    while not (blocks in past_blocks):
        loops += 1
        # add copy of current distribution
        past_blocks.append(list(blocks))
        # find the largest block
        largest_block_position = 0
        largest_block_size = blocks[0]
        for i,s in enumerate(blocks):
            if (s > largest_block_size):
                largest_block_position = i
                largest_block_size = s
        # redistribute the block
        to_redistribute = largest_block_size
        position = largest_block_position
        blocks[position] = 0
        while (to_redistribute != 0):
            position += 1
            if position >= len(blocks):
                position = 0
            blocks[position] += 1
            to_redistribute -= 1

    # print number of loops before a repeat
    print("Number Of Loops Before First Repeat:", loops)

    # now keep track of number of loops in each cycle
    cycle_count = 0
    block_target = list(blocks)
    next_block = None
    while (block_target != next_block):
        # find the largest block
        largest_block_position = 0
        largest_block_size = blocks[0]
        for i,s in enumerate(blocks):
            if (s > largest_block_size):
                largest_block_position = i
                largest_block_size = s
        # redistribute the block
        to_redistribute = largest_block_size
        position = largest_block_position
        blocks[position] = 0
        while (to_redistribute != 0):
            position += 1
            if position >= len(blocks):
                position = 0
            blocks[position] += 1
            to_redistribute -= 1
        # set temp variable to check if it matches target
        next_block = list(blocks)
        cycle_count += 1
    
    # print how many cycles before repeat
    print("Number Of Loops In Final Cycle:", cycle_count)