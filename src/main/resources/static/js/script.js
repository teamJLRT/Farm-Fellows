function changeTime(id, timeLeft){
    console.log("hello");
    const elem = document.getElementById(id);
    let time = timeLeft;
    function updateTime(){
        elem.innerHTML = time;
        time -= 1;
    }
    setInterval(updateTime, 1000);
}

let id;
let timeLeft;