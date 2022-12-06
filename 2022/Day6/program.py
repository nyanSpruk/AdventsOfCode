def function(lines, msg_len):
    for line in lines:
        string = ""
        counter = 0
        for c in line:
            counter += 1
            string += c
            if len(string) == msg_len:
                if len(set(string)) == len(string):
                    break
                string = string[1:]
        print(counter)


def main():
    test_input_path = "./test_inp.txt"
    real_input_path = "./input.txt"

    with open(test_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines, 4)
        print()
        function(lines, 14)
    print("Real data:")
    with open(real_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines, 4)
        print()
        function(lines, 14)


if __name__ == "__main__":
    main()
