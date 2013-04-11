function TrackView(canvasID){
	this.canvas = document.getElementById(canvasID);
	this.context = canvas.getContext('2d');
	
	alert("foo");
	alert(this.context);
}


