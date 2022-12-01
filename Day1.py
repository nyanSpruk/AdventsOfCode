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
    test_input_path = "test_inp.txt"
    test_input = open(test_input_path, "r")

    input_path = "input.txt"
    input_file = open(input_path, "r")
    part_one(input_file)
    input_file = open(input_path, "r")
    part_two(input_file)


if __name__ == "__main__":
    main()
