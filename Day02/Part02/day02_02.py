given_file = open("input.txt", "r")
ans = 0

# get list of integers from each line
nums = list(map(int, given_file.readline().split()))

while nums:

    # get result of all numbers that evenly divide
    mini_nums = [a // b for a in nums for b in nums if a != b and a % b == 0]

    # should only be one per line
    ans += mini_nums[0]

    # get next line
    nums = list(map(int, given_file.readline().split()))

# print answer
print("Answer:", ans)

# close file
given_file.close()
