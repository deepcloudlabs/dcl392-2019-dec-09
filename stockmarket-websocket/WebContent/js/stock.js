var StockViewModel = function(){
	var self= this;
	
	self.symbol= ko.observable();
	self.company= ko.observable();
	self.description= ko.observable();
	self.price= ko.observable();
	self.stocks= ko.observableArray([]);
	self.stockLookup= {};
	self.toJson= function(){
		return JSON.stringify({
			symbol: self.symbol(),
			company: self.company(),
			description: self.description(),
			price: self.price(),
		});
	}

	self.wsBase= 'ws://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/stockmarket';
	self.websocket= new WebSocket(self.wsBase);
	
	self.websocket.onopen = function(event){
		console.log('Connected!');
		$.ajax({
			method: 'GET',
			url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks',
			success: (stocks) => {
				stocks.forEach(stock => self.stockLookup[stock.symbol]= stock);
			}
		});
	}
	self.websocket.onmessage = (event)=>{
		var e = JSON.parse(event.data);
		var stock = { symbol: e.symbol,
				      description: self.stockLookup[e.symbol].description,
				      company: self.stockLookup[e.symbol].company,
				      price: e.oldPrice,
				      newPrice: e.newPrice};
		var stocks = self.stocks().filter( source => source.symbol!=stock.symbol );
		stocks.push(stock);
		self.stocks(stocks);
	}
	
	self.disconnect= function(){
		
	}
	
	self.findStock= function(){
		$.ajax({
		   method: 'GET',
		   url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks/'+self.symbol(),
		   success: function(stock){
			   self.company(stock.company);
			   self.price(stock.price);
			   self.description(stock.description);
		   }
		});
	}
	self.findAllStocks= function(){
		$.ajax({
			method: 'GET',
			url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks',
			success: function(stocks){
				stocks.map(stock => stock.newPrice='');
				self.stocks(stocks);
			}
		});
	};
	self.removeStock= function(){
		$.ajax({
			method: 'DELETE',
			url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks/'+self.symbol(),
			success: function(stock){
				self.company(stock.company);
				self.price(stock.price);
			}
		});
	}
	self.addStock= function(){
		$.ajax({
			method: 'POST',
			url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks',
			contentType: 'application/json',
            data: self.toJson(),			
			success: function(stock){				
			}
		});
	};
	self.updateStock= function(){
		$.ajax({
			method: 'PUT',
			url: 'http://localhost:8080/stockmarket-websocket-0.0.1-SNAPSHOT/api/v1/stocks',
			contentType: 'application/json',
			data: self.toJson(),			
			success: function(stock){			
			}
		});
	};
};