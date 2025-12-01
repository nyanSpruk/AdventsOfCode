import * as fs from "fs";

const testData: string = "./data/day1/testData.txt";
const realData: string = "./data/day1/realData.txt";
const testDataLines: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataLines: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part2 = (lines: string[]): number => {
    let counter = 0;
    let currentPosition = 50;

    for (const line of lines) {
        const rotation = line.substring(0, 1);
        const number = parseInt(line.substring(1));
        const dir = rotation === "R" ? 1 : -1;

        for (let i = 0; i < number; i++) {
            currentPosition = (currentPosition + dir + 100) % 100;
            if (currentPosition === 0) {
                counter++;
            }
        }
    }
    return counter;
};

const part1 = (lines: string[]): number => {
    let counter = 0;
    let value = 50;

    for (const line of lines) {
        const rotation = line.substring(0, 1);
        const number = parseInt(line.substring(1));
        const dir = rotation === "R" ? 1 : -1;
        const movement = dir * number;

        value += movement;

        if (value < 0) {
            value %= 100;
            value += 100;
        }
        if (value >= 100) {
            value %= 100;
        }
        if (value === 0) {
            counter++;
        }
    }
    return counter;
};
console.log(`Part 1 Test Data Counter: ${part1(testDataLines)}`);
console.log(`Part 1 Real Data Counter: ${part1(realDataLines)}`);

console.log(`Part 2 Test Data Counter: ${part2(testDataLines)}`);
console.log(`Part 2 Real Data Counter: ${part2(realDataLines)}`);
