function changeTime(id, buttonid, timeLeft){
    console.log("hello");
    const elem = document.getElementById(id);
    const buttonElm = document.getElementById(buttonid);
    let time = timeLeft;
    function updateTime(){
        elem.innerHTML = time;
        time -= 1;
        if(time < 0){
        buttonElm.disabled = false;
        elem.style.color = "red";
        }
    }
    setInterval(updateTime, 1000);
}

function updatePage(){
    const elem = document.getElementById("newprice-indicator");
    let time = 60 * 60;
    function updatePriceMessage(){
        elem.innerHTML = "Prices update in " + time + " seconds.";
        time -= 1;
        if (time == 0){
            document.location.reload();
        }
    }
    setInterval(updatePriceMessage, 1000);
}

let id;
let timeLeft;
let buttonid;

