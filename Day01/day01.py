givenString = open("input.txt", "r").read()

total = 0

for i, c in enumerate(givenString):
    if givenString[i] == givenString[i-1]:
        total += int(givenString[i])

print(total)
