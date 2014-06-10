/* ==== slider nameSpace ==== */
var slider = function() {
	/* ==== private methods ==== */
	function getElementsByClass(object, tag, className) {
		var o = object.getElementsByTagName(tag);
		for ( var i = 0, n = o.length, ret = []; i < n; i++) {
			if (o[i].className == className) ret.push(o[i]);
		}
		if (ret.length == 1) ret = ret[0];
		return ret;
	}
	var slides = [];
	var S, S0, iW, iH, oP, oc, frm, NF, view, Z;
	var wh, ht, wr, r, mx, mn; 
	/* ==== animation loop ==== */
	var run = function () {
		Z += (mn - Z) * .5;
		view.calc();
		var i = NF;
		while (i--) slides[i].move();
	}
	/* ==== resize  ==== */	
	var resize = function () {
		wh = oc.clientWidth;
		ht = oc.clientHeight;
		wr = wh * iW;
		r  = ht / wr;
		mx = wh / NF;
		mn = (wh * (1 - iW)) / (NF - 1);
	}

	/* ==== Slide Constructor ==== */
	Slide = function (N) {
		this.N      = N;
		this.x0     = this.x1 = N * mx;
		this.v      = 0;
		this.loaded = false;
		this.cpt    = 0;
		this.start  = new Date();
		this.obj    = frm[N];
		this.txt    = getElementsByClass(this.obj, 'div', 'text');
		this.img    = getElementsByClass(this.obj, 'img', 'diapo');
		this.bkg    = document.createElement('div');
		this.bkg.className = 'backgroundText';
		this.obj.insertBefore(this.bkg, this.txt);
		if (N == 0) this.obj.style.borderLeft = 'none';
		this.obj.style.left = Math.floor(this.x0) + 'px';
		/* ==== mouse events ==== */
		this.obj.parent = this;
		this.obj.onmouseover = function() {
			this.parent.over();
			return false;
		}
	}
	/* ==== target positions ==== */
	Slide.prototype.calc = function () {
		for (var i = 0; i <= this.N; i++)
			slides[i].x1 = i * Z;
		for (var i = this.N + 1; i < NF; i++)
			slides[i].x1 = wh - (NF - i) * Z;
	}
	/* ==== HTML animation : move slides ==== */
	Slide.prototype.move = function() {
		var s = (this.x1 - this.x0) / S;
		/* ==== lateral slide ==== */
		if (this.N && Math.abs(s) > .5)
			this.obj.style.left = Math.floor(this.x0 += s) + 'px';
		/* ==== vertical text ==== */
		var v = (this.N < NF - 1) ? slides[this.N + 1].x0 - this.x0 : wh - this.x0;
		if (Math.abs(v - this.v) > .5) {
			this.bkg.style.top = this.txt.style.top = Math.floor(2 + ht - (v - Z) * iH * r) + 'px';
			this.v = v;
			this.cpt++;
		} else {
			if (!this.pro) {
				/* ==== adjust speed ==== */
				this.pro = true;
				var tps = new Date() - this.start;
				if(this.cpt > 1) {
					S = Math.max(2, (28 / (tps / this.cpt)) * S0);
				}
			}
		}
		if (!this.loaded) {
			if (this.img.complete) {
				this.img.style.visibility = 'visible';
				this.loaded = true;
			}
		}
	}
	/* ==== over ==== */
	Slide.prototype.over = function () {
		resize();
		view = this;
		this.start = new Date();
		this.cpt = 0;
		this.pro = false;
		this.calc();
	}
	/* ==== start script ==== */
	var init = function (oCont, speed, iw, ih, op) {
		S   = S0 = speed;
		iW  = iw;
		iH  = ih;
		oP  = op;
		oc  = document.getElementById(oCont);
		frm = getElementsByClass(oc, 'div', 'slide');
		NF  = frm.length;
		resize();
		for (var i = 0; i < NF; i++) 
			slides[i] = new Slide(i);
		view = slides[0];
		Z = mx;
		setInterval(run, 32);
	}
	/* ==== public methods ==== */
	return {
		init : init
	}
}();