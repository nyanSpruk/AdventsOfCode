import * as fs from "fs";

const day = 8;

const testData: string = `./data/day${day}/testData.txt`;
const realData: string = `./data/day${day}/realData.txt`;
const testDataPairs: string[] = fs.readFileSync(testData, "utf-8").split("\n");
const realDataPairs: string[] = fs.readFileSync(realData, "utf-8").split("\n");

const euclideanDistance = (coordinates: number[], coordinates2: number[]): number => {
    return Math.sqrt(Math.pow(coordinates[0] - coordinates2[0], 2) +
        Math.pow(coordinates[1] - coordinates2[1], 2) +
        Math.pow(coordinates[2] - coordinates2[2], 2));
};

type coord = { x: number, y: number, z: number };
type coordList = coord[];

const calculateDistanceMap = (coords: coordList): Map<string, number> => {
    const distMap: Map<string, number> = new Map();
    for (let i = 0; i < coords.length; i++) {
        for (let j = i + 1; j < coords.length; j++) {
            const key = `${coords[i].x}-${coords[j].x},${coords[i].y}-${coords[j].y},${coords[i].z}-${coords[j].z}`;
            const dist = euclideanDistance([coords[i].x, coords[i].y, coords[i].z],
                [coords[j].x, coords[j].y, coords[j].z]);
            distMap.set(key, dist);
        }
    }
    return distMap;
}

const part1 = (lines: string[], iterations: number): number => {
    let val = 0;
    let coords: coordList = [];
    let listOfIntersections: coordList[] = [];
    for (let line of lines) {
        if (line.trim() === "") {
            continue;
        }
        const [x, y, z] = line.split(",").map(Number);
        coords.push({ x, y, z });
        listOfIntersections.push([{ x, y, z }]);
    }
    let distMap = calculateDistanceMap(coords);

    for (let iter = 0; iter < iterations; iter++) {
        let shortestDistance = Infinity;
        let closestPair1: coordList | null = null;
        let closestPair2: coordList | null = null;
        let closestPairIx1 = -1;
        let closestPairIx2 = -1;
        for (let i = 0; i < listOfIntersections.length; i++) {
            const points = listOfIntersections[i];
            for (let j = i + 1; j < listOfIntersections.length; j++) {
                const points2 = listOfIntersections[j];
                for (let p1 of points) {
                    for (let p2 of points2) {
                        const key = `${p1.x}-${p2.x},${p1.y}-${p2.y},${p1.z}-${p2.z}`;
                        const dist = distMap.get(key);
                        if (dist !== undefined && dist < shortestDistance) {
                            shortestDistance = dist;
                            closestPair1 = points;
                            closestPair2 = points2;
                            closestPairIx1 = i;
                            closestPairIx2 = j;
                        }

                    }
                }
            }
        }
        listOfIntersections.splice(closestPairIx2, 1);
        listOfIntersections.splice(closestPairIx1, 1);
        if (closestPair1 && closestPair2) {
            const combined = closestPair1.concat(closestPair2);
            listOfIntersections.push(combined);
        }


    }

    const sizes: number[] = listOfIntersections.map(intersection => intersection.length);
    sizes.sort((a, b) => b - a);
    val = sizes[0] * sizes[1] * sizes[2];
    // Console log sizes
    console.log("Final intersection sizes: ", sizes);
    return val;
}


console.log("Test Data Part 1: ", part1(testDataPairs, 10));
console.log("Real Data Part 1: ", part1(realDataPairs, 1000));

// console.log("Test Data Part 2: ", part2(testDataPairs));
// console.log("Real Data Part 2: ", part2(realDataPairs));
