class Instruction:
    
    def __init__(self, reg1, action, amount, reg2, comparison, comparison_value):
        self.reg1 = reg1
        self.action = action
        self.amount = amount
        self.reg2 = reg2 
        self.comparison = comparison
        self.comparison_value = comparison_value


def read_values(filename):
    file1 = open(filename, 'r')
    instructions = []
    next_line = file1.readline()
    while (next_line):
        split_line = next_line.split()
        instruction = Instruction(  split_line[0], 
                                    split_line[1], 
                                    int(split_line[2]), 
                                    split_line[4], 
                                    split_line[5],
                                    int(split_line[6]))
        instructions.append(instruction)
        next_line = file1.readline()
    return instructions


def set_up_registers(instructions):
    registers = {}
    for instruction in instructions:
        registers[instruction.reg1] = 0
    return registers


def perform_instruction(instruction, registers):
    # print(registers[instruction.reg2], instruction.comparison, instruction.comparison_value)
    if (eval(str(registers[instruction.reg2]) + instruction.comparison + str(instruction.comparison_value))):
        if (instruction.action == "inc"):
            registers[instruction.reg1] += instruction.amount
        else:
            registers[instruction.reg1] -= instruction.amount


if __name__ == "__main__":
    
    # first read all instructions
    instructions = read_values("input.txt")

    # then set all registers to initial value of 0
    registers = set_up_registers(instructions)

    # keep track of largest value
    largestEver = None

    # perform all the instructions
    for instruction in instructions:
        perform_instruction(instruction, registers)
        if (largestEver == None or registers[instruction.reg1] > largestEver):
            largestEver = registers[instruction.reg1]

    # find the largest register
    largestCurrent = None
    for k,v in registers.items():
        # print("Value:", v)
        if largestCurrent == None or v > largestCurrent:
            largestCurrent = v

    # print largest register values
    print("Largest Register Value Ever:", largestEver)
    print("Largest Register Value Currently:", largestCurrent)
