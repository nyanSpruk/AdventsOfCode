import * as fs from "fs";

const day = 2;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split(",");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split(",");

const checkIfInvalid1 = (number: number): boolean => {
    const strNum = number.toString();
    const firstHalf = strNum.substring(0, strNum.length / 2);
    const secondHalf = strNum.substring(strNum.length / 2);
    if (firstHalf === secondHalf) {
        return true;
    }
    return false;
};

const checkNumbers = (number: number): number => {
    let sum = 0;

    const strNum = number.toString();
    const len = strNum.length;

    for (let subLen = 1; subLen <= Math.floor(len / 2); subLen++) {
        const subStr = strNum.substring(0, subLen);
        let repeatedStr = "";
        while (repeatedStr.length < len) {
            repeatedStr += subStr;
        }
        if (repeatedStr === strNum) {
            sum += parseInt(subStr);
        }
    }
    return sum;
};

const part1 = (pairs: string[]): number => {
    let sum = 0;
    for (const pair of pairs) {
        const [first, second] = pair.split("-");
        const start = parseInt(first);
        const end = parseInt(second);

        for (let i = start; i <= end; i++) {
            // console.log(`Checking number: ${i}`);
            if (checkIfInvalid1(i)) {
                // console.log(`Invalid number: ${i}`);
                sum += i;
            }
        }
    }
    return sum;
};

const part2 = (pairs: string[]): number => {
    let sum = 0;
    for (const pair of pairs) {
        const [first, second] = pair.split("-");
        const start = parseInt(first);
        const end = parseInt(second);
        // console.log(`~~~~~Processing range: ${start} to ${end}~~~~~~`);
        for (let i = start; i <= end; i++) {
            // console.log(`Checking number: ${i}`);
            const addition = checkNumbers(i);
            if (addition > 0) {
                // console.log(`Adding ${addition} for number: ${i}`);
                sum += i;
            }
        }
    }
    return sum;
};

// Added after doing both parts
const bothParts = (pairs: string[]): { part1: number; part2: number } => {
    let sum1 = 0;
    let sum2 = 0;
    for (const pair of pairs) {
        const [first, second] = pair.split("-");
        const start = parseInt(first);
        const end = parseInt(second);

        for (let i = start; i <= end; i++) {
            if (checkIfInvalid1(i)) {
                sum1 += i;
            }
            if (checkNumbers(i) > 0) {
                sum2 += i;
            }
        }
    }
    return { part1: sum1, part2: sum2 };
};

console.log(`Part 1 Test Data Sum: ${part1(testDataPairs)}`);
console.log(`Part 1 Real Data Sum: ${part1(realDataPairs)}`);

console.log(`Part 2 Test Data Sum: ${part2(testDataPairs)}`);
console.log(`Part 2 Real Data Sum: ${part2(realDataPairs)}`);

const testBoth = bothParts(testDataPairs);
console.log(`Both Parts Test Data - Part 1: ${testBoth.part1}, Part 2: ${testBoth.part2}`);

const realBoth = bothParts(realDataPairs);
console.log(`Both Parts Real Data - Part 1: ${realBoth.part1}, Part 2: ${realBoth.part2}`);
