from enum import Enum


def convert_input(play: str, is_opponent):
    if is_opponent:
        if play == "A":
            return Item.ROCK
        elif play == "B":
            return Item.PAPER
        elif play == "C":
            return Item.SCISSORS
    else:
        if play == "X":
            return Item.ROCK
        elif play == "Y":
            return Item.PAPER
        elif play == "Z":
            return Item.SCISSORS


def determine_winner(opponent, us):
    if opponent == Item.ROCK:
        if us == Item.ROCK:
            return Result.DRAW.value
        elif us == Item.PAPER:
            return Result.WIN.value
        elif us == Item.SCISSORS:
            return Result.LOSE.value
    elif opponent == Item.PAPER:
        if us == Item.ROCK:
            return Result.LOSE.value
        elif us == Item.PAPER:
            return Result.DRAW.value
        elif us == Item.SCISSORS:
            return Result.WIN.value
    elif opponent == Item.SCISSORS:
        if us == Item.ROCK:
            return Result.WIN.value
        elif us == Item.PAPER:
            return Result.LOSE.value
        elif us == Item.SCISSORS:
            return Result.DRAW.value


def pick_move(us, opponent):
    # us == X -> we need to lose
    # us == Y -> we need to draw
    # us == Z -> we need to win
    if us == "Y":
        return opponent
    elif us == "X":
        if opponent == Item.ROCK:
            return Item.SCISSORS
        elif opponent == Item.PAPER:
            return Item.ROCK
        elif opponent == Item.SCISSORS:
            return Item.PAPER
    elif us == "Z":
        if opponent == Item.ROCK:
            return Item.PAPER
        elif opponent == Item.PAPER:
            return Item.SCISSORS
        elif opponent == Item.SCISSORS:
            return Item.ROCK


class Result(Enum):
    WIN = 6
    DRAW = 3
    LOSE = 0


class Item(Enum):
    ROCK = 1
    PAPER = 2
    SCISSORS = 3


def part_two(file):
    lines = file.read()
    rounds = lines.split("\n")
    score = 0
    for play in rounds:
        moves = play.split(" ")
        opponent_inp = convert_input(moves[0], True)
        our_inp = convert_input(moves[1], False)
        move_to_make = pick_move(moves[1], opponent_inp)
        score += Item[move_to_make.name].value + determine_winner(
            opponent_inp, move_to_make
        )
    print(score)


def part_one(file):
    lines = file.read()
    rounds = lines.split("\n")
    score = 0
    for play in rounds:
        moves = play.split(" ")
        opponent_inp = convert_input(moves[0], True)
        our_inp = convert_input(moves[1], False)
        score += Item[our_inp.name].value + determine_winner(opponent_inp, our_inp)
    print(score)


def main():
    test_input_path = "test_inp.txt"
    real_input_path = "input.txt"

    with open(real_input_path, "r", encoding="utf-8") as file:
        part_two(file)


if __name__ == "__main__":
    main()
