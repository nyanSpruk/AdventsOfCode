def improved(file) -> list([int]):
    elf_cal = []
    lines = file.read()
    elfs = lines.split("\n\n")

    for elf in elfs:
        elf_split = elf.split("\n")
        sum_cal = sum(int(x) for x in elf_split if x)
        elf_cal.append(sum_cal)
    elfs_sorted = sorted(elf_cal, reverse=True)
    return elfs_sorted


def get_sum(elfs_sorted, count, isTest=False):
    sum_first_count = sum(elfs_sorted[:count])
    print(
        f'{"TEST-DATA " if isTest else "" }First {count} elf calories sum: {sum_first_count}'
    )


def part_one(file):
    highest_sum = 0
    sum = 0
    for line in file:
        # print(line)
        if len(line) == 1:
            if sum > highest_sum:
                highest_sum = sum
            sum = 0
        else:
            sum += int(line)

    print("Part one: " + str(highest_sum))


def part_two(file):
    top_3_sums = [0, 0, 0]

    sum = 0
    for line in file:
        # print(line)
        if len(line) == 1:
            if sum > top_3_sums[0]:
                top_3_sums[2] = top_3_sums[1]
                top_3_sums[1] = top_3_sums[0]
                top_3_sums[0] = sum
            elif sum > top_3_sums[1]:
                top_3_sums[2] = top_3_sums[1]
                top_3_sums[1] = sum
            elif sum > top_3_sums[2]:
                top_3_sums[2] = sum
            sum = 0
        else:
            sum += int(line)
    sum_top_3 = top_3_sums[0] + top_3_sums[1] + top_3_sums[2]
    print("Part two: " + str(sum_top_3))


def main():
    test_input_path = "./Day1/test_inp.txt"
    real_input_path = "./Day1/input.txt"

    with open(test_input_path, "r", encoding="utf-8") as file:
        test_data = improved(file)

    get_sum(test_data, 1, True)
    get_sum(test_data, 3, True)
    print()
    with open(real_input_path, "r", encoding="utf-8") as file:
        data = improved(file)

    get_sum(data, 1)
    get_sum(data, 3)


if __name__ == "__main__":
    main()
