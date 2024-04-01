function footToMeter(foot) {
    let meter = 0.305 * foot;
    return meter;
}

function meterToFoot(meter) {
    let foot = 3.279 * meter;
    return foot;
}
let temp = prompt("Nhập phương thức bạn muốn đổi: ( 1 => F to M ; 2 => M to F ");
if (temp == 1) {
    let foot = prompt("Nhập foot: ");
    alert(foot + " foot " + "= " + footToMeter(foot) + " meter");
}else if(temp==2){
    let meter = prompt("Nhập meter: ");
    alert(meter + " meter " + "= " + meterToFoot(meter) + " foot");
}else{
    document.write("LỖI");
}