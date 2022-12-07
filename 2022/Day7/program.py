def function(lines):
    hashmap = {}
    active_dirs = []
    dir_count = 0
    for line in lines:
        # If line starts  with "$ cd" then add it to the hashmap
        if line == "$ cd ..":
            active_dirs.pop()
            dir_count += 1
            continue
        elif line.startswith("$ cd"):
            active_dirs.append(line.split(" ")[2])
            # Combine the active dirs to get the key
            key = ""
            for active_dir in active_dirs:
                key += active_dir
            # key += line.split(" ")[2]
            hashmap[key] = 0
            continue
        elif line == "$ ls":
            continue
        elif line.startswith("d"):
            continue
        else:
            key = ""
            for active_dir in active_dirs:
                key += active_dir
                hashmap[key] = int(hashmap[key]) + int(line.split(" ")[0])

    summed = sum([int(hashmap[key]) for key in hashmap if int(hashmap[key]) <= 100000])
    print(summed)

    total_space = 70000000
    free_space = total_space - int(hashmap["/"])
    needed_space = 30000000 - free_space

    sorted_hashmap = sorted(hashmap.items(), key=lambda x: x[1])
    for key, value in sorted_hashmap:
        if value >= needed_space:
            print(value)
            break


def main():
    test_input_path = "./test_inp.txt"
    real_input_path = "./input.txt"

    with open(test_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines)
    with open(real_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines)


if __name__ == "__main__":
    main()
