inputFile = open("input.txt", "r")
line = inputFile.readline()

checksum = 0

while line:

    # find largest and smallest value for each line
    minValue = None
    maxValue = 0
    for num in list(map(int, line.split())):
        minValue = num if minValue == None or num <= minValue else minValue
        maxValue = num if num >= maxValue else maxValue

    # add diff to running checksum
    checksum += maxValue - minValue

    # get next line
    line = inputFile.readline()

print("Checksum:", checksum)
