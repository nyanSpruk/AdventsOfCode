import * as fs from "fs";

const day = 3;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const getMaxForN = (line: string, n: number): number => {
    const lineLength = line.length;
    const stack: number[] = [];

    for (let i = 0; i < lineLength; i++) {
        const digit = Number.parseInt(line[i]);

        while (stack.length > 0 && stack[stack.length - 1] < digit && stack.length + (lineLength - i) > n) {
            stack.pop();
        }
        stack.push(digit);

        while (stack.length > n) {
            stack.pop();
        }
    }
    // console.log(`Stack for line ${line} and n=${n}: ${stack}`);
    let result = 0;
    for (const digit of stack) {
        result = result * 10 + digit;
    }
    // console.log(`For line ${line} and n=${n}, max number is ${result}`);
    return result;
};

const part1 = (lines: string[]): number => {
    let sum = 0;
    for (const line of lines) {
        let max = 0;
        const digits = line.split("").map(Number);
        for (let i = 0; i < digits.length; i++) {
            for (let j = i + 1; j < digits.length; j++) {
                const num = 10 * digits[i] + digits[j];
                if (num > max) max = num;
            }
        }
        sum += max;
    }
    return sum;
};

const part2 = (lines: string[], k: number): number => {
    let sum = 0;
    for (const line of lines) {
        const maxNum = getMaxForN(line, k);
        sum += maxNum;
    }
    return sum;
};
console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs, 12));
console.log("Real Data Part 2: ", part2(realDataPairs, 12));
