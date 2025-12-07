import * as fs from "fs";

const day = 7;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part1 = (lines: string[]): number => {
    const startLocation = { x: lines[0].indexOf("S"), y: 0 };
    let currentLocations = [startLocation];
    let setUniqueLocations = new Set<string>();
    for (let row = 1; row < lines.length; row++) {
        const line = lines[row];
        let newLocations: Array<{ x: number; y: number }> = [];
        for (const loc of currentLocations) {
            const belowChar = line[loc.x];
            if (belowChar === ".") {
                newLocations.push({ x: loc.x, y: row });
            } else if (belowChar === "^") {
                const key = `${row},${loc.x}`;
                if (setUniqueLocations.has(key)) {
                    continue;
                }
                setUniqueLocations.add(key);
                if (loc.x > 0) {
                    newLocations.push({ x: loc.x - 1, y: row });
                }
                if (loc.x < line.length - 1) {
                    newLocations.push({ x: loc.x + 1, y: row });
                }
            }
        }
        currentLocations = newLocations;
    }

    return setUniqueLocations.size;
};

const part2 = (lines: string[]): number => {
    const visitedPaths = new Map<string, number>();
    const startLocation = { x: lines[0].indexOf("S"), y: 0 };

    const countPaths = (x: number, y: number): number => {
        if (y === lines.length - 1) {
            return 1;
        }

        const key = `${x},${y}`;
        if (visitedPaths.has(key)) {
            return visitedPaths.get(key)!;
        }

        let paths = 0;
        const belowChar = lines[y + 1][x];

        if (belowChar === ".") {
            paths = countPaths(x, y + 1);
        } else if (belowChar === "^") {
            if (x > 0) {
                paths += countPaths(x - 1, y + 1);
            }
            if (x < lines[y + 1].length - 1) {
                paths += countPaths(x + 1, y + 1);
            }
        }

        visitedPaths.set(key, paths);
        return paths;
    };

    return countPaths(startLocation.x, startLocation.y);
};

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));
