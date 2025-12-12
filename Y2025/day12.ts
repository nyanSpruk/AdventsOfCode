import * as fs from "fs";

const day = 12;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

type StorageRegion = {
    width: number;
    height: number;
    presents: number[];
};

const part1 = (lines: string[]): number => {
    let sum = 0;

    const presentSizes: number[] = [];
    const storages: StorageRegion[] = [];
    for (let i = 0; i < lines.length; i++) {
        if (presentSizes.length < 6) {
            // Skipping index and empty lines
            if (i % 5 === 0 || i % 5 === 4) continue;

            const shapeLines = lines.slice(i, i + 3);
            const size = shapeLines
                .join("")
                .split("")
                .filter((char) => char === "#").length;
            presentSizes.push(size);
            i += 3;
        } else {
            const line = lines[i].trim();
            const [grid, rest] = line.split(":").map((s) => s.trim());
            const nums = rest.split(" ").map((n) => parseInt(n));
            const [width, height] = grid.split("x").map((n) => parseInt(n));
            storages.push({ width, height, presents: nums });
        }
    }

    for (const storage of storages) {
        const availableArea = storage.width * storage.height;
        let neededArea = 0;

        for (let j = 0; j < storage.presents.length; j++) {
            neededArea += presentSizes[j] * storage.presents[j];
        }

        if (neededArea <= availableArea) sum++;
    }

    return sum;
};

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));
