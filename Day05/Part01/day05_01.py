def get_jumps():
    input_file = open('input.txt', 'r')
    jumps = []
    next_number = input_file.readline()
    while (next_number):
        jumps.append(int(next_number))
        next_number = input_file.readline()
    input_file.close
    return jumps


if __name__ == '__main__':

    # get list of all jump sizes
    jumps = get_jumps()

    next_jump = 0
    jump_count = 0
    current_pos = 0

    # stop looping after jumping out of range
    while (0 <= current_pos < len(jumps)):     
        next_jump = jumps[current_pos]
        jump_count += 1
        jumps[current_pos] += 1
        current_pos += next_jump

    # print answer
    print("Number of Loops:", jump_count)
