class Circle {
    x;
    y;
    radius;
    color;

    constructor(x, y, radius, color) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
        this.canvas = document.getElementById("canvas")
        this.ctx = canvas.getContext("2d")
        this.flag = 'up';
    }

    draw() {
        this.ctx.beginPath();
        this.ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2);
        this.ctx.fillStyle = this.color;
        this.ctx.fill();
    }

    moveTop() {
        this.y -= 2;
        this.x += 2;
    }

    moveDown() {
        this.y += 2;
        this.x -= 2;
    }

    move() {
        this.ctx.clearRect(0, 0, this.canvas.width, this.canvas.height);
        console.log(this.y)
        if (this.y - this.radius <= 0) {
            this.flag = "down"
        } else if(this.y + this.radius >= this.canvas.height) {
            this.flag = "up"
        } else if (this.x - this.radius <= 0) {
            this.flag = "down"
        }

        switch (this.flag) {
            case "down":
                this.moveDown();
                break;
            default:
                this.moveTop();

        }

        this.draw();
    }


}