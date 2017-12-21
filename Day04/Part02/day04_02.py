import itertools


def get_anagrams(word):
    return ["".join(i) for i in itertools.permutations(word)]


if __name__ == '__main__':
    
    input_file = open("input.txt", "r")

    next_line = input_file.readline()

    valid_passphrase_count = 0

    while (next_line):

        passphrase = next_line.split()
        valid_passphrase = True
        used_words = []

        for word in passphrase:
            if word in used_words:
                valid_passphrase = False
            used_words.extend(get_anagrams(word))

        if valid_passphrase:
            valid_passphrase_count += 1

        next_line = input_file.readline()

    input_file.close()

    print("Answer:", valid_passphrase_count)
