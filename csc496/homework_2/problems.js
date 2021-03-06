'use strict';

/*
 *  Problem 1
 *  Create a variable to store the radius of a circle. Assign the variable some value. Calculate and display the the circle's diameter, circumference, and area.
 */
function problemOne() {
    var radius = 50;

    var diameter = 2 * radius;
    var circumference = Math.PI * diameter;
    var area = Math.PI * radius * radius;

    return 'Diameter: ' + diameter + '<br>Circumference: ' + circumference + '<br>Area: ' + area;
}

/*
 *  Problem 2
 *  Write a function that takes one argument, a temperature in Celsius, and returns that temperature in Fahrenheit. Print out the resulting temperature. Feel free to hard code the Celsius temperature, or if you wish accept the temperature as input via a text box.
 */
function problemTwo(input) {
    return '' + getFahrenheight(input) + '&nbsp;&deg;F';

    function getFahrenheight(celcius) {
        return celcius * 9 / 5 + 32;
    }
}

/*
 *  Problem 3
 *  Write a function that takes one argument, the user's birthday year. The function shall return an array whose elements are the possible ages of the user. Only the user's birthday should be hardcoded -- not 2014. Utilize JavaScript functions to return the current calendar year (2014).
 */
function problemThree(input) {
    var year = new Date().getFullYear();

    return prettifyArray(birthday(input));

    function birthday(birthYear) {
        var year = new Date().getFullYear();

        return [year - birthYear - 1, year - birthYear];
    }
}

/*
 *  Problem 4
 *  Write a function that takes one numeric argument and displays that number reversed in an alert box. (i.e. 12345 -> 54321)
 */
function problemFour(input) {
    alert(reverse(input));

    return 'Done.';

    function reverse(number) {
        return number.toString().split('').reverse().join('');
    }
}

/*
 *  Problem 5
 *  Write a function that generates three random numbers and outputs the largest number.
 */
function problemFive() {
    var numbers = [];

    for (var i = 0; i < 3; i++) {
        numbers.push(Math.floor(Math.random() * 1000) + 1);
    }

    return prettifyArray(numbers) + '<br>' + Math.max.apply(Math, numbers);
}

/*
 *  Problem 6
 *  Write a function that generates all combinations of a given String, and returns the combinations as elements in an array. (i.e. 'bad' -> ['b','ba','bad','a','ad','d'])
 */
function problemSix(input) {
    return prettifyArray(generateCombinations(input));

    function generateCombinations(str) {
        var combinations = [];

        combine('', str);

        combinations.sort(function (a, b) {
            return a.length - b.length;
        });

        return combinations;

        function combine(pre, str) {
            if (str.length > 0) {
                combinations.push(pre + str[0]);

                var rest = str.substring(1, str.length);

                combine(pre + str[0], rest);
                combine(pre, rest);
            }
        }
    }
}

/*
 *  Problem 7
 *  Write a function that accepts one argument and returns the typeof the argument.
 */
function problemSeven(input) {
    return getType(input);

    function getType(arg) {
        return typeof arg;
    }
}

/*
 *  Problem 8
 *  Write a function that takes an array of numbers and finds the second lowest and second greatest numbers. The function should return an object with this information.
 */
function problemEight(input) {
    var seconds = findSeconds(input);

    return prettifyArray(input) + '<br>Second Lowest: ' + seconds.secondLowest + '<br>Second Greatest: ' + seconds.secondGreatest;

    function findSeconds(arr) {
        arr.sort(function (a, b) {
            return a < b;
        });

        return {
            secondLowest: arr[arr.length - 2],
            secondGreatest: arr[1]
        };
    }
}

/*
 *  Problem 9
 *  Write a function that takes two arguments, an array with numeric elements and a number. The function should iterate over the array, and in-place, delete all elements in the array that are smaller than the second argument number.
 */
function problemNine(arr, n) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] < n) {
            arr.splice(i, 1);
            i--;
        }
    }

    return prettifyArray(arr);
}

/*
 *  Problem 10
 *  Write a function that takes one numeric argument, n, and outputs the first n happy numbers.
 */
function problemTen(n) {
    var numbers = [];
    var num = 0;

    while (numbers.length < n) {
        if (isHappy(num)) {
            numbers.push(num);
        }

        num++;
    }

    return prettifyArray(numbers);
}

/*
 *  Problem 11
 *  Duplicate your above function so that it also incorporates a try/catch block to check for a negative n.
 */
function problemEleven(n) {
    var numbers = [];
    var num = 0;

    try {
        if (n < 0) {
            throw 'n cannot be negative.';
        }

        while (numbers.length < n) {
            if (isHappy(num)) {
                numbers.push(num);
            }

            num++;
        }
    } catch (err) {
        alert('Please enter a value greater than 0.');
    }

    return prettifyArray(numbers);
}

var depth = 0;
var maxDepth = 1000;

function isHappy(n) {
    var digits = n.toString().split('');
    var sum = 0;

    for (var i in digits) {
        sum += digits[i] * digits[i];
    }

    if (sum !== 1 && depth < maxDepth) {
        depth++;

        return isHappy(sum);
    }

    depth = 0;

    return sum === 1;
}

/*
 *  Problem 12
 *  Write a function that accepts a number as input and returns a numeric-like string that has "pluses" + inserted between every two even digits. (i.e. 2647522 -> 2+6+4752+2)
 */
function problemTwelve(input) {
    return insertPlus(input);

    function insertPlus(number) {
        var arr = number.split('');

        for (var i = 0; i < arr.length; i++) {
            if (arr[i] % 2 === 0 && arr[i - 1] % 2 === 0) {
                arr.splice(i, 0, '+');
                i--;
            }
        }

        return arr.join('');
    }
}

/*
 *  Problem 13
 *  Write a function that accepts an array argument and returns the most frequent item. (i.e. [5, 'a', 'a', 'a', 3, 1, 'a', 4, 'a', 4, 4] -> "a 5")
 */
function problemThirteen(input) {
    var max = frequent(input);

    return max.key + ': ' + max.value;

    function frequent(arr) {
        var count = {};

        for (var i in arr) {
            if (count[arr[i]]) {
                count[arr[i]]++;
            } else {
                count[arr[i]] = 1;
            }
        }

        var max = {
            key: '',
            value: 0
        };

        for (var i in count) {
            if (count[i] > max.value) {
                max.key = i;
                max.value = count[i];
            }
        }

        return max;
    }
}

/*
 *  Problem 14
 *  Write a function that prints the properties of an object by iterating over them.
 */
function problemFourteen(obj) {
    var output = '';

    for (var i in obj) {
        output += obj[i] + '<br>';
    }

    return output;
}
