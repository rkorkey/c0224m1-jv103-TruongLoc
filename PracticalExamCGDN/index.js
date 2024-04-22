function handleSelectionChange() {
    const exercise = document.getElementById("exerciseSelect").value;
    const output = document.getElementById("output");
    const canvas = document.getElementById("canvas");
    canvas.style.display = 'none';
    output.innerHTML = '';

    if (!exercise) {
        output.textContent = 'Vui lòng chọn một bài tập trước khi xác nhận!';
        return;
    }

    switch(exercise) {
        case 'exercise1':
            output.textContent = 'Điểm trung bình là: ' + findAvg([90, 80, 70, 85, 95]);
            break;
        case 'exercise2':
            output.textContent = 'Mảng sau khi thêm: ' + insertNumberToArray([1, 2, 4, 5], 3, 2).join(", ");
            break;
        case 'exercise3':
            output.textContent = 'Tổng các số nguyên tố từ 0 đến 10 là: ' + sumOfListPrime(10);
            break;
        case 'exercise4':
            canvas.style.display = 'block';
            const circle = new Circle(100, 100, 80, '#000000');
            circle.render(canvas);
            break;
    }
}
//---------------------------------------- BAI 1 ---------------------------------
function findAvg(scores) {
    let sum = 0;
    for (let i = 0; i < scores.length; i++) {
        sum += scores[i];
    }
    return sum / scores.length;
}
//---------------------------------------- BAI 2 ---------------------------------
function insertNumberToArray(arr, x, index) {
    if (index < 0 || index > arr.length) return arr;
    let newArr = [...arr.slice(0, index), x, ...arr.slice(index)];
    return newArr;
}
//---------------------------------------- BAI 3 ---------------------------------
function isPrime(num) {
    if (num < 2) return false;
    for (let i = 2; i * i <= num; i++) {
        if (num % i === 0) return false;
    }
    return true;
}

function sumOfListPrime(x) {
    let sum = 0;
    for (let i = 2; i <= x; i++) {
        if (isPrime(i)) {
            sum += i;
        }
    }
    return sum;
}
//---------------------------------------- BAI 4 ---------------------------------
class Circle {
    constructor(x, y, radius, color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    render(canvas) {
        const ctx = canvas.getContext('2d');
        ctx.beginPath();
        ctx.arc(this.x, this.y, this.radius, 0, 2 * Math.PI);
        ctx.fillStyle = this.color;
        ctx.fill();
    }
}
