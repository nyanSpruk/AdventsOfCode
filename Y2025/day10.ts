import * as fs from "fs";

const day = 10;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const part1 = (lines: string[]): number => {
    let sum = 0;

    for (let line of lines) {
        const parts = line.split(" ");

        const lightsStr = parts[0].replace("[", "").replace("]", "");
        const targetState = lightsStr.split("").map((c) => (c === "#" ? "1" : "0")).join("");
        const numLights = targetState.length;

        const buttons: number[][] = [];
        for (let i = 1; i < parts.length - 1; i++) {
            const indexes = parts[i].replace("(", "").replace(")", "").split(",").map(Number);
            buttons.push(indexes);
        }

        const numButtons = buttons.length;
        let minPresses = Infinity;
        const combinations = 2 ** numButtons;
        for (let mask = 0; mask < combinations; mask++) {
            let currentState = "0".repeat(numLights).split("");
            let presses = 0;

            for (let i = 0; i < numButtons; i++) {
                if (mask & (1 << i)) {
                    for (const idx of buttons[i]) {
                        currentState[idx] = currentState[idx] === "0" ? "1" : "0";
                    }
                    presses++;
                }
            }

            if (currentState.join("") === targetState) {
                minPresses = Math.min(minPresses, presses);
            }
        }

        sum += minPresses;
    }

    return sum;
};

const part2 = (lines: string[]): number => {
    let sum = 0;
    // No idea, later

    return sum;
};

console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));