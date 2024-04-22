const canvas = document.getElementById('canvas');
const ctx = canvas.getContext("2d")
let circle1 = new Circle(100, 200, 20, "black");
circle1.draw();


setInterval(() => {
    circle1.move();
}, 10)