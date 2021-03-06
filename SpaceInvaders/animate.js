var canvasH = 500;
var canvasW = 400;

var canvas = document.getElementById('screen');
var ctx = canvas.getContext('2d');

var single = AudioFX('sounds/laser.ogg'); //ogg sound files supported in firefox
function ship(x, y){
    this.width = 20;
    this.height = 20;
    
	//top left corner
    this.x = x;
    this.y = y;
    
	//bottom right corner
    this.x2 = this.x+this.width;
    this.y2 = this.y+this.height;
	
	this.canFire = true;
	this.alive = true;
    this.draw = function(){
        ctx.fillStyle= "red";
        ctx.fillRect(this.x, this.y, this.width, this.height);
    };
    this.draw();

    this.update = function(){
		if(this.y == 470){
			if(Key.isDown(Key.LEFT)) this.moveLeft();
			if(Key.isDown(Key.RIGHT)) this.moveRight();
			this.checkFire();
		}else{
			this.collide(shot);
		}
        this.draw();
    };

    this.moveLeft = function(){
        //console.log("moving left");
		if(this.x>0){
            this.x = this.x-3.2;
            this.x2 = this.x2-3.2;
        }
	}
    this.moveRight = function(){
        if(this.x2<canvasW){
            this.x = this.x+3.2;
            this.x2 = this.x2+3.2;
        }
    };

    this.fire = function(){
        shot.velocity = -7;
		single.play();
		console.log("fire");
    };

    this.checkFire = function(){
    	if(!Key.isDown(Key.F)){
			this.canFire = true;
		}else if(Key.isDown(Key.F) && shot.y == this.y && this.canFire){
			this.canFire = false
			this.fire();
		}
	}
	
	this.collide = function(laser){
		if(laser.x >= this.x && laser.x <= this.x2 && laser.y >= this.y  && laser.y <= this.y2){
			console.log("collide1");
		}else if(laser.x2 >= this.x && laser.x2 <= this.x2 && laser.y >= this.y  && laser.y <= this.y2){
			console.log("collide2");
		}
	}

}

function shipLaser(ship){
    this.ship = ship;
    this.width = 5;
    this.height = 15;
	//top left corner
    this.x = this.ship.x+7;
	this.y = this.ship.y;
	//bottom right corner
	this.x2 = this.x+this.width;
	this.y2 = this.y+this.height;

    this.velocity = 0;

    this.alive = true;
    

    this.update = function(){
       this.move();
       this.reset();
       this.draw();
       //console.log("drawing");
       
    };
    this.move = function(){
        this.y = this.y+this.velocity;
		this.y2 = this.y+this.height;
        /*
		if(this.y == player1.y){
            this.x = player1.x+ 7;
        }
		*/
		if(this.y == this.ship.y){
			this.x = this.ship.x+7;
			this.x2 = this.x+this.width;
		}
    };

    this.reset = function(){
		if(this.y<0 || this.y > 500){
			this.x = this.ship.x+7;
			this.x2 = this.x + this.width;
			this.y = this.ship.y;
			this.y2 = this.y + this.height;
			this.velocity = 0;
		}
    }
    this.draw = function(){
        ctx.fillStyle = "green";
        ctx.fillRect(this.x, this.y, this.width, this.height);
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
    //returns if key is pressed down
    isPressed: function(keyCode){
        return this._pressed[keyCode];
    }
};

var player1  = new ship(280, 470);
var alien1 = new ship(280, 200);
var shot = new shipLaser(player1);

setInterval(function(){

    ctx.clearRect(0, 0, canvasW, canvasH);
    player1.update();
	alien1.update();
    shot.update();

}, 25);

window.addEventListener('keypress', function(event) {Key.onKeypress(event); }, false); 
window.addEventListener('keyup', function(event) {Key.onKeyup(event); }, false);
window.addEventListener('keydown', function(event) {Key.onKeydown(event); }, false);

