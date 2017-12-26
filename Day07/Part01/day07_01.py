import re


class Program:

    def __init__(self, name, weight, programs):
        self.name = name
        self.weight = weight
        self.programs = programs

    @classmethod
    def fromList(cls, string_list):
        name = string_list[0]
        weight = string_list[1]
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
    if (program.programs) == []:
        return 0
    else:
        depth = 0
        for s in program.programs:
            d = get_depth(program_dict[s], program_dict)
            if (d > depth):
                depth = d
        return 1 + depth


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
    print("Answer:", program.name)
