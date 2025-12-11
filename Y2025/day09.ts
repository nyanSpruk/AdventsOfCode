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
    const red: [number, number][] = [];
    for (const line of lines) {
        if (line.trim() === "") continue;
        const [x, y] = line.split(",").map(Number);
        red.push([x, y]);
    }

    // Saw on reddit people mentioning coordinate compression, so I decided to give it a try
    const uniqueX = [...new Set(red.map(([x]) => x))].sort((a, b) => a - b);
    const uniqueY = [...new Set(red.map(([, y]) => y))].sort((a, b) => a - b);

    const compressedX = new Map(uniqueX.map((n, i) => [n, i + 1]));
    const compressedY = new Map(uniqueY.map((n, i) => [n, i + 1]));
    const revX = new Map(uniqueX.map((n, i) => [i + 1, n]));
    const revY = new Map(uniqueY.map((n, i) => [i + 1, n]));

    const corners = red.map(([x, y]) => [
        compressedX.get(x)!,
        compressedY.get(y)!,
    ]);

    // Drawing the shape

    let maxI = 0;
    let maxJ = 0;
    const color = new Set<string>();

    for (let k = 0; k < corners.length; k++) {
        const [x, y] = corners[k];
        const [nextX, nextY] = corners[(k + 1) % corners.length];

        // Here i draw vertical green line
        if (nextX === x) {
            const [y1, y2] = [y, nextY].sort((a, b) => a - b);
            maxI = Math.max(maxI, x);
            maxJ = Math.max(maxJ, y2);
            for (let yi = y1; yi <= y2; yi++) {
                color.add(`${x},${yi}`);
            }
        } else {
            // Here i draw horizontal green line
            const [x1, x2] = [x, nextX].sort((a, b) => a - b);
            maxI = Math.max(maxI, x2);
            maxJ = Math.max(maxJ, y);
            for (let xi = x1; xi <= x2; xi++) {
                color.add(`${xi},${y}`);
            }
        }
    }

    const redSet = new Set(corners.map(([x, y]) => `${x},${y}`));

    // Filling in the shape
    for (let y = 1; y <= maxJ; y++) {
        let insidePolygon = false;
        let enteredFromTop = false;

        for (let x = 1; x <= maxI; x++) {
            const key = `${x},${y}`;

            const isCorner = redSet.has(key);
            const isEdge = color.has(key);
            const isAbove = color.has(`${x},${y - 1}`);
            const isBelow = color.has(`${x},${y + 1}`);

            if (isCorner) {
                if (!insidePolygon) {
                    enteredFromTop = isAbove;
                    insidePolygon = true;
                } else {
                    // If corner bends up or down, we are exiting
                    const cornerExitDown = isAbove && enteredFromTop && !isBelow;
                    const cornerExitUp = !enteredFromTop && !isAbove;

                    if (cornerExitDown || cornerExitUp) {
                        insidePolygon = false;
                    }
                }
            } else if (isEdge) {
                insidePolygon = !insidePolygon;
            } else if (insidePolygon) {
                color.add(key);
            }
        }
    }

    let maxArea = 0;
    for (let i = 0; i < corners.length; i++) {
        for (let j = i + 1; j < corners.length; j++) {
            const [x1, y1] = corners[i];
            const [x2, y2] = corners[j];

            const [minX, maxX] = [x1, x2].sort((a, b) => a - b);
            const [minY, maxY] = [y1, y2].sort((a, b) => a - b);

            let allColored = true;
            for (let x = minX; x <= maxX && allColored; x++) {
                for (let y = minY; y <= maxY && allColored; y++) {
                    if (!color.has(`${x},${y}`)) {
                        allColored = false;
                        break;
                    }
                }
                if (!allColored) break;
            }

            if (!allColored) continue;
            const actualX1 = revX.get(minX)!;
            const actualX2 = revX.get(maxX)!;
            const actualY1 = revY.get(minY)!;
            const actualY2 = revY.get(maxY)!;
            const area = (actualY2 - actualY1 + 1) * (actualX2 - actualX1 + 1);
            maxArea = Math.max(maxArea, area);
        }
    }

    return maxArea;
};

// console.log("Test Data Part 1: ", part1(testDataPairs));
// console.log("Real Data Part 1: ", part1(realDataPairs));

console.log("Test Data Part 2: ", part2(testDataPairs));
console.log("Real Data Part 2: ", part2(realDataPairs));
