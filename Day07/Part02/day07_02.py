import re


class Program:

    def __init__(self, name, weight, programs):
        self.name = name
        self.weight = weight
        self.programs = programs

    @classmethod
    def fromList(cls, string_list):
        name = string_list[0]
        weight = int(string_list[1])
        programs = []
        for i in range(2, len(string_list)):
            if (string_list[i] != ''):
                programs.append(string_list[i])
        return cls(name, weight, programs)


def read_input(filename):
    f = open(filename, "r")
    programs = {}
    line = f.readline()
    while (line):
        split_line = re.split("\\W+", line)
        program = Program.fromList(split_line)
        programs[program.name] = program
        line = f.readline()
    f.close()
    return programs


def get_depth(program, program_dict):
    if (program.programs == []):
        return 0
    else:
        depth = 0
        for s in program.programs:
            d = get_depth(program_dict[s], program_dict)
            if (d > depth):
                depth = d
        return 1 + depth


def get_weight(program, program_dict):
    if (program.programs == []):
        return program.weight
    else:
        temp_weight = 0
        for s in program.programs:
            temp_weight += get_weight(program_dict[s], program_dict)
        return program.weight + temp_weight


def get_next_unbalanced(program, program_dict):
    weights = []
    for pstring in program.programs:
        weights.append(get_weight(program_dict[pstring], program_dict))
    for i,w in enumerate(weights):
        if weights.count(w) == 1:
            return program_dict[program.programs[i]]
    return None


if __name__ == "__main__":
    
    programs = read_input("input.txt")
    
    # find base as program with greatest depth
    depth = -1
    program = None
    for k,v in programs.items():
        d = get_depth(v, programs)
        if (d > depth):
            depth = d
            program = v
    
    # print result
    print("Base Program:", program.name)

    # find stack of unbalanced programs
    unbalanced_stack = [program]
    next_unknown = get_next_unbalanced(program, programs)

    while(next_unknown):
        unbalanced_stack.append(next_unknown)
        next_unknown = get_next_unbalanced(next_unknown, programs)

    # get the bad weight
    bad_weight = get_weight(unbalanced_stack[len(unbalanced_stack)-1], programs)
    print("Bad Weight:", bad_weight)

    # get the target weight
    target_weight = 0
    for pstring in unbalanced_stack[len(unbalanced_stack)-2].programs:
        target_weight = get_weight(programs[pstring], programs)
        if (target_weight != bad_weight):
            break
    print("Target Weight:", target_weight)

    # calculate required program weight to offset difference
    print("Required Program Weight:", unbalanced_stack[len(unbalanced_stack)-1].weight + (target_weight - bad_weight))
