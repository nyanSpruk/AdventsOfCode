import * as fs from "fs";

const day = 6;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part1 = (lines: string[]): number => {
    let sum = 0;
    let columns: string[] = [];
    for (const line of lines) {
        const newLine = line.replace(/\s+/g, " ").trim().split(" ");
        for (let i = 0; i < newLine.length; i++) {
            if (columns[i] === undefined) {
                columns[i] = "";
            }
            columns[i] += newLine[i] + " ";
        }
    }

    for (const col of columns) {
        const parts = col.trim().split(" ");
        const operator = parts.pop();
        let colVal = operator === "*" ? 1 : 0;
        for (const part of parts) {
            const num = Number.parseInt(part);
            if (operator === "*") {
                colVal *= num;
            } else {
                colVal += num;
            }
        }
        sum += colVal;
    }
    return sum;
};

const part2 = (lines: string[]): number => {
    let sum = 0;
    let numbers: number[] = [];
    let operator: string = "";
    let blockValue: number = 0;
    for (let colIndex = 0; colIndex < lines[0].length; colIndex++) {
        let colNumStr = "";
        for (let rowIndex = 0; rowIndex < lines.length; rowIndex++) {
            colNumStr += lines[rowIndex][colIndex];
            if (lines[rowIndex][colIndex] === "+" || lines[rowIndex][colIndex] === "*") {
                operator = lines[rowIndex][colIndex];
                blockValue = operator === "*" ? 1 : 0;
                break;
            }
        }
        const colNum = Number.parseInt(colNumStr);
        numbers.push(colNum);
        if (isNaN(colNum) || colIndex === lines[0].length - 1) {
            numbers = numbers.filter((n) => !isNaN(n));
            for (const num of numbers) {
                blockValue = operator === "*" ? blockValue * num : blockValue + num;
            }
            sum += blockValue;
            numbers = [];
            blockValue = 0;
            operator = "";
            continue;
        }
    }

    return sum;
};

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));
