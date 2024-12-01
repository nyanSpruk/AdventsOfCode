def get_data(lines):
    # Find empty line
    empty_line = 0
    for i, line in enumerate(lines):
        if line == "":
            empty_line = i
            break
    # Number of stacks
    num_stacks = int(lines[empty_line - 1].split(" ")[-2])
    # List of stacks
    stacks = [[] * num_stacks for _ in range(num_stacks)]
    # First part
    for i in range(empty_line - 2, -1, -1):
        split_line = lines[i].split(" ")
        num_letters = 0
        num_of_empty = 0
        for ix, c in enumerate(split_line):
            if c != "":
                if num_of_empty % 4 == 0 and num_of_empty != 0:
                    num_letters += num_of_empty // 4
                letter = c[1]
                stacks[num_letters].append(letter)
                num_letters += 1
                num_of_empty = 0
            else:
                num_of_empty += 1
    # Second part
    moves = []
    for i in range(empty_line + 1, len(lines)):
        split = lines[i].split(" ")

        moves.append([int(x) for x in split if x.isdigit()])

    return num_stacks, stacks, moves


def part_one(num_stacks, stacks, moves):
    for move in moves:
        num_of_moves = move[0]
        start = move[1] - 1
        end = move[2] - 1
        for _ in range(num_of_moves):
            stacks[end].append(stacks[start].pop())

    for i in range(num_stacks):
        if len(stacks[i]) > 0:
            print(stacks[i][-1], end="")
    print()


def part_two(num_stacks, stacks, moves):
    for move in moves:
        num_of_moves = move[0]
        start = move[1] - 1
        end = move[2] - 1
        ix = len(stacks[start]) - num_of_moves
        stacks[end] += stacks[start][ix:]
        stacks[start] = stacks[start][:ix]

    for i in range(num_stacks):
        if len(stacks[i]) > 0:
            print(stacks[i][-1], end="")
    print()


def main():
    test_input_path = "./test_inp.txt"
    real_input_path = "./input.txt"

    with open(test_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        num_moves, moves, stacks = get_data(lines)
        part_one(num_moves, moves, stacks)
        num_moves, moves, stacks = get_data(lines)
        part_two(num_moves, moves, stacks)
    with open(real_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        num_moves, moves, stacks = get_data(lines)
        part_one(num_moves, moves, stacks)
        num_moves, moves, stacks = get_data(lines)
        part_two(num_moves, moves, stacks)


if __name__ == "__main__":
    main()
