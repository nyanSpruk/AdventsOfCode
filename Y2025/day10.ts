import * as fs from "fs";
import { solve, equalTo } from "yalps";
import type { Model, Constraint } from "yalps";
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

    for (let line of lines) {
        const parts = line.split(" ");

        const targetStr = parts[parts.length - 1].replace("{", "").replace("}", "");
        const targets = targetStr.split(",").map(Number);
        const numCounters = targets.length;

        const buttons: number[][] = [];
        for (let i = 1; i < parts.length - 1; i++) {
            const indexes = parts[i].replace("(", "").replace(")", "").split(",").map(Number);
            buttons.push(indexes);
        }

        const constraints: Record<string, Constraint> = {};
        const variables: Record<string, Record<string, number>> = {};

        // Target constraints
        for (let counter = 0; counter < numCounters; counter++) {
            constraints[`counter${counter}`] = equalTo(targets[counter]);
        }

        for (let button = 0; button < buttons.length; button++) {
            const coeffs: Record<string, number> = {
                objective: 1,
            };

            // Which counter which button
            for (const counter of buttons[button]) {
                coeffs[`counter${counter}`] = 1;
            }

            variables[`button${button}`] = coeffs;
        }

        const model: Model = {
            direction: "minimize",
            objective: "objective",
            constraints: constraints,
            variables: variables,
            integers: true,
        };

        const solution = solve(model);

        if (solution.status === "optimal") {
            sum += solution.result;
        }
    }

    return sum;
};


console.log("Test Data Part 1: ", part1(testDataPairs));
console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));