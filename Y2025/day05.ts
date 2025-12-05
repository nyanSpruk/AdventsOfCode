import * as fs from "fs";

const day = 5;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");




const part1 = (lines: string[]): number => {
    let sum = 0;
    let doingRanges = true;
    let ranges: Array<[number, number]> = [];
    for (let line of lines) {
        if (line.trim() === "") {
            doingRanges = false;
            for (let i = 0; i < ranges.length; i++) {
                for (let j = i + 1; j < ranges.length; j++) {
                    const [minA, maxA] = ranges[i];
                    const [minB, maxB] = ranges[j];
                    if (!(maxA < minB || minA > maxB)) {
                        ranges[i] = [Math.min(minA, minB), Math.max(maxA, maxB)];
                        ranges.splice(j, 1);
                        j--;
                    }
                }
            }
            continue;
        }

        if (doingRanges) {
            const [start, end] = line.split("-");
            const min = parseInt(start);
            const max = parseInt(end);
            let merged = false;
            for (let i = 0; i < ranges.length; i++) {
                const [rangeMin, rangeMax] = ranges[i];
                if (!(max < rangeMin || min > rangeMax)) {
                    ranges[i] = [Math.min(rangeMin, min), Math.max(rangeMax, max)];
                    merged = true;
                    break;
                }
            }
            if (!merged) {
                ranges.push([min, max]);
            }
            
        } else {
            const num = parseInt(line);
            for (const [rangeMin, rMax] of ranges) {
                if (num >= rangeMin && num <= rMax) {
                    sum++;
                    break;
                }
            }
        }
    }
    part2(ranges);
    return sum;
};

const part2  = (ranges: Array<[number, number]>): void => {
    let sum = 0;
    for (const [rangeMin, rangEMax] of ranges) {
        sum += (rangEMax - rangeMin + 1);
    }
    console.log("Data Part 2: ", sum);

}

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

// console.log("Test Data Part 2: ", part2(testDataPairs));
// console.log("Real Data Part 2: ", part2(realDataPairs));