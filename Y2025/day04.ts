import * as fs from "fs";

const day = 4;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const updatedLines = (lines: string[]): string[] => {
    const newLines: string[] = [];
    for (let row = 0; row < lines.length; row++) {
        let newLine = '';
        for (let col = 0; col < lines[row].length; col++) {
            if (lines[row][col] === '.') {
                newLine += '.';
            } else if (checkAround(lines, row, col)) {
                newLine += '.';
            } else {
                newLine += lines[row][col];
            }
        }
        newLines.push(newLine);
    }
    return newLines;
}

const printLines = (lines: string[]): void => {
    for (const line of lines) {
        console.log(line);
    }
}


const checkAround = (lines: string[], row: number, col: number): boolean => {
    let emptyCount = 0;
    for (let r = row - 1; r <= row + 1; r++) {
        for (let c = col - 1; c <= col + 1; c++) {
            if (r === row && c === col) {
                continue;
            }
            if (r < 0 || r >= lines.length || c < 0 || c >= lines[0].length) {
                emptyCount++;
            } else if (lines[r][c] === '.') {
                emptyCount++;
            }
        }
    }
    return emptyCount >= 5;
}

const part1 = (lines: string[]): number => {
    let sum = 0;
    for (let row = 0; row < lines.length; row++) {
        for (let col = 0; col < lines[row].length; col++) {
            if (lines[row][col] === '.')
                continue;
            if (checkAround(lines, row, col)) {
                sum++;
            }
        }
    }
    return sum;
};

const part2 = (lines: string[]): number => {
    let totalSum = 0;
    let currentLines = lines;
    while (true) {
        const sum = part1(currentLines);
        if (sum === 0) {
            break;
        }
        totalSum += sum;
        currentLines = updatedLines(currentLines);
    }
    return totalSum;
}

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));