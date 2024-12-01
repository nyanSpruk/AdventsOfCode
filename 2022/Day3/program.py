def calculate_score(char):
    score = 1 if char.islower() else 27
    case = "a" if char.islower() else "A"
    return ord(char) - ord(case) + score


def function2(lines):
    # Divide lines into groups of 3
    groups_of_lines = []
    for i in range(0, len(lines), 3):
        groups_of_lines.append(lines[i : i + 3])
    score = 0
    for group in groups_of_lines:
        for char in group[0]:
            if char in group[1] and char in group[2]:
                score += calculate_score(char)
                break
    print("Part two", score)


def function(lines):
    score = 0
    for line in lines:
        first_half = line[: len(line) // 2]
        second_half = line[len(line) // 2 :]

        visited = []
        for char in first_half:
            if char in second_half and char not in visited:
                visited.append(char)

                score += calculate_score(char)
    print("Part one", score)


def main():
    test_input_path = "./test_inp.txt"
    real_input_path = "./input.txt"

    with open(test_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines)
        function2(lines)
    with open(real_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines)
        function2(lines)


if __name__ == "__main__":
    main()
