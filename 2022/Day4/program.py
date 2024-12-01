def function(lines):
    pairs = [[sec.split("-") for sec in line.split(",")] for line in lines]
    count = 0
    for sec1, sec2 in pairs:
        s11, s12 = sec1
        s21, s22 = sec2
        if (int(s11) <= int(s21) and int(s22) <= int(s12)) or (
            int(s21) <= int(s11) and int(s12) <= int(s22)
        ):
            count += 1
    print(count)


def function2(lines):
    pairs = [[sec.split("-") for sec in line.split(",")] for line in lines]
    count = 0
    for sec1, sec2 in pairs:
        s11, s12 = sec1
        s21, s22 = sec2
        if int(s11) <= int(s22) and int(s21) <= int(s12):
            count += 1

    print(count)


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
