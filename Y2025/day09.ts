import * as fs from "fs";

const day = 9;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part1 = (lines: string[]): number => {
    let coordinates: number[][] = [];
    for (let line of lines) {
        if (line.trim() === "") {
            continue;
        }
        const [x, y] = line.split(",").map(Number);
        coordinates.push([x, y]);
    }
    // Sort from biggest to smallest x then y
    coordinates.sort((a, b) => {
        if (a[0] === b[0]) {
            return a[1] - b[1];
        }
        return a[0] - b[0];
    });


    let maxArea = 0;
    for (let i = 0; i < coordinates.length; i++) {
        const cord1 = coordinates[i];
        for (let j = i + 1; j < coordinates.length; j++) {
            const cord2 = coordinates[j];
            const area = (Math.abs(cord2[0] - cord1[0]) + 1) * (Math.abs(cord2[1] - cord1[1]) + 1);
            // console.log(`Area between ${cord1} and ${cord2}: ${area}`);
            if (area > maxArea) {
                maxArea = area;
            }
        }
    }
    return maxArea;
};

const part2 = (lines: string[]): number => {

    return 0;

}

// connectAndVisualizeAllPoints(testDataPairs);

// console.log("Test Data Part 1: ", part1(testDataPairs));
// console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));
