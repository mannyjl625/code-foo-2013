var canvasH = 500;
var canvasW = 400;

var canvas = document.getElementById('screen');
var ctx = canvas.getContext('2d');

function ship(){
    this.width = 20;
    this.height = 20;
    //top left corner
    this.x = 280;
    this.y = 470;
    //bottom right corner
    this.x2 = this.x+this.width;
    this.y2 = this.y+this.height;

    this.canFire = true;

    this.draw = function(){
        ctx.fillStyle= "red";
        ctx.fillRect(this.x, this.y, this.width, this.height);
    };
    this.draw();

    this.update = function(){
        if(Key.isDown(Key.LEFT)) this.moveLeft();
        if(Key.isDown(Key.RIGHT)) this.moveRight();
        
        if(Key.isPressed(
        this.draw();
    };

    this.moveLeft = function(){
        if(this.x>0){
            this.x = this.x-3.2;
            this.x2 = this.x2-3.2;
        }
    };

    this.moveRight = function(){
        if(this.x2<400){
            this.x = this.x+3.2;
            this.x2 = this.x2+3.2;
        }
    };

    this.fire = function(){
        laser shot = new laser();
        setTimeout(function(){
            this.canFire = true;;

        }, 3000);

    };
}

function laser(){
    
    this.width = 2;
    this.height = height;

    this.x = player1.x+10;
    this.y = player1.y;
    
    

    this.move = function(){
        this.y = this.y+3;      
    };
    
     


}

var Key = {
    //array that keeps track of key presses
    _pressed: {},

    //controls
    F: 70,
    RIGHT: 39,
    LEFT: 37,
    
    //returns if key is down/true or up/false)
    isDown: function(keyCode){
        return this._pressed[keyCode];
    },
    //sets array index to true when key is pressed
    onKeydown: function(event){
        this._pressed[event.keyCode] = true;
    },
    //unsets array index when key is released
    onKeyup: function(event){
        delete this._pressed[event.keyCode];
    },
    //sets array index when button is presses, unsets if unpressed
    onKeypress: function(event){
        if(!this._pressed[event.which]){
            delete this._pressed[event.which];  //use event.which for firefox keyPress
        }else{                                  //event.keyCode for IE onKeyPress
            this._pressed[event.which] = true;
        }
    },
    //returs if key is pressed down
    isPressed: function(keyCode){
        return this._pressed[keyCode];
    }
};

var player1  = new ship();

setInterval(function(){

    ctx.clearRect(0, 0, canvasW, canvasH);
    player1.update();

}, 25);
window.addEventListener('keypress', function(event) {Key.onKeypress(event); }, false); 
window.addEventListener('keyup', function(event) {Key.onKeyup(event); }, false);
window.addEventListener('keydown', function(event) {Key.onKeydown(event); }, false);
