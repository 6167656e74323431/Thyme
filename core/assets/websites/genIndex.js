/*
 *
 * 	Thyme is an educational game to assist teenagers in time management, and tracking.
 * 	Copyright (C) 2019 Theodore Preduta, Larry Yuan
 *
 * 	This program is free software: you can redistribute it and/or modify
 * 	it under the terms of the GNU Affero General Public License as published
 * 	by the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 * 	This program is distributed in the hope that it will be useful,
 * 	but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 	GNU Affero General Public License for more details.
 *
 * 	You should have received a copy of the GNU Affero General Public License
 * 	along with this program.  If not, see <https://www.gnu.org/licenses/>.
 * /
 */
const fs = require('fs');
const path = require('path');
let walk = (dir) => {
    const result = [];

    const files = [dir];
    do {
        const filepath = files.pop();
        const stat = fs.lstatSync(filepath);
        if (stat.isDirectory()) {
            fs
                .readdirSync(filepath)
                .forEach(f => files.push(path.join(filepath, f)));
        } else if (stat.isFile()) {
            result.push(path.relative(dir, filepath));
        }
    } while (files.length !== 0);

    return result.filter(x => x.length !== 0);
};

const websites = fs.readdirSync(__dirname).filter(x => !fs.lstatSync(
    __dirname + "/" + x
).isFile());

for (let website of websites) {
    fs.writeFileSync(websites + "/index", walk(__dirname + "/" + website).join("\n"));
}