def function(lines):
    hashmap = {}
    active_dirs = []
    ls = True
    for line in lines:
        # If line starts  with "$ cd" then add it to the hashmap
        if line == "$ cd ..":
            active_dirs.pop()
            continue
        elif line.startswith("$ cd"):
            active_dirs.append(line.split(" ")[2])
            key = line.split(" ")[2]
            hashmap[key] = 0
            continue
        elif line == "$ ls":
            ls = True
            continue
        if(ls):
            if line.startswith("d"):
                continue
            else:
                for active_dir in active_dirs:
                    hashmap[active_dir] = int(hashmap[active_dir]) + int(line.split(" ")[0])
    summed = sum([int(hashmap[key]) for key in hashmap if hashmap[key] <= 100000])
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
        # function2(lines)
    with open(real_input_path, "r", encoding="utf-8") as file:
        lines = file.read()
        lines = lines.split("\n")
        function(lines)
        # function2(lines)


if __name__ == "__main__":
    main()
