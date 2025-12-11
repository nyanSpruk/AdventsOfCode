import * as fs from "fs";

const day = 11;

const testData_p1: string = `./data/day${day}/testData1.txt`;
const testData_p2: string = `./data/day${day}/testData2.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs1: string[] = fs.readFileSync(testData_p1, "utf-8").split("\n");
const testDataPairs2: string[] = fs.readFileSync(testData_p2, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part1 = (lines: string[]): number => {
    let sumPaths = 0;
    let mapOfValues: Map<string, string[]> = new Map();
    for (let line of lines) {
        const keysAndValues = line.split(": ")
        const key = keysAndValues[0];
        const values = keysAndValues[1].split(" ");
        mapOfValues.set(key, values);
    }

    const stack: string[][] = [];
    stack.push(["you"]);
    while (stack.length > 0) {
        const path = stack.pop()!;
        const lastNode = path[path.length - 1];
        if (lastNode === "out") {
            sumPaths++;
            continue;
        }
        const connections = mapOfValues.get(lastNode);
        if (connections) {
            for (const connection of connections) {
                const newPath = [...path, connection];
                stack.push(newPath);
            }
        }
    }

    return sumPaths;
};

const part2 = (lines: string[]): number => {
    let sumPaths = 0;
    let mapOfValues: Map<string, string[]> = new Map();
    let memo: Map<string, number> = new Map();

    for (let line of lines) {
        const keysAndValues = line.split(": ");
        const key = keysAndValues[0];
        const values = keysAndValues[1].split(" ");
        mapOfValues.set(key, values);
    }

    const dfs = (node: string, visited: Set<string>): number => {
        const key = node + "|" + Array.from(visited).sort().join(",");
        if (memo.has(key)) return memo.get(key)!;

        if (node === "out") {
            return visited.has("dac") && visited.has("fft") ? 1 : 0;
        }

        let count = 0;
        const connections = mapOfValues.get(node);
        if (connections) {
            for (const connection of connections) {
                const newVisited = new Set(visited);
                if (connection === "dac" || connection === "fft") {
                    newVisited.add(connection);
                }
                count += dfs(connection, newVisited);
            }
        }

        memo.set(key, count);
        return count;
    };

    sumPaths = dfs("svr", new Set());
    return sumPaths;
};

console.log("Test Data Part 1: ", part1(testDataPairs1));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs2));
console.log("Real Data Part 2: ", part2(realDataPairs));